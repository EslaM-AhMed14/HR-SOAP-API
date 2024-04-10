package com.example.persistence.Service;

import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DAOs.implementation.DepartmentDAO;
import com.example.persistence.DAOs.implementation.EmployeeDAO;
import com.example.persistence.DTOs.DepartmentDto;
import com.example.persistence.DTOs.EmployeeDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Employee;
import com.example.persistence.mappers.DepartmentMapper;
import com.example.persistence.mappers.EmployeeMapper;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

public class DepartmentService {



    public static List<DepartmentDto> getAllDepartments() {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Department> deps =  departmentDAO.getAllDepartments(em);
            return DepartmentMapper.INSTANCE.toDepartmentDtoList(deps);
        });
    }

    public static DepartmentDto getDepartmentById(int id) {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.get(id, em).orElse(null);
            if (department == null) {
                return null;
            }
            return DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
        });
    }

    public static DepartmentDto getDepartmentByName(String name) {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.getDepartmentByName(name, em);
            if (department == null) {
                return null;
            }
            return DepartmentMapper.INSTANCE.departmentToDepartmentDto(department);
        });
    }

    public static boolean addDepartment(DepartmentDto departmentDto) throws ResourceNotFound {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("DB");
        jakarta.persistence.EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = DepartmentMapper.INSTANCE.departmentDtoToDepartment(departmentDto);
            if(department.getEmployee().getEmployeeId() != null){
                EmployeeDAO employeeDAO = new EmployeeDAO();
                Employee existingEmployee = employeeDAO.get(department.getEmployee().getEmployeeId(), em).orElse(null);
                if(existingEmployee == null){
                    throw new ResourceNotFound("Employee not found" , "ERR1");
                }
                boolean find =  departmentDAO.isManager(department.getEmployee().getEmployeeId(), em);
                if(find){
                    throw new ResourceNotFound("Employee is already a manager for another department", "ERR1");
                }

                department.setEmployee(existingEmployee);
            }
            boolean done =  departmentDAO.save(department, em);
            em.getTransaction().commit();
            em.close();
            if(!done){
                throw new ResourceNotFound("Department already exists", "ERR1");
            }else {
                System.out.println("Department add: " +department.getDepartmentId()+ " " + department.getDepartmentName());
                EmployeeService.updateEmployeeDepartment(department.getEmployee().getEmployeeId(), department.getDepartmentId());
                return true;
            }
    }

    public static boolean updateDepartment(DepartmentDto departmentDto) {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = DepartmentMapper.INSTANCE.departmentDtoToDepartment(departmentDto);
            Department fromDatabase = departmentDAO.get(department.getDepartmentId(), em).orElse(null);
            if (fromDatabase == null) {
                throw new ResourceNotFound("Department not found" , "ERR1");
            }
            department = updateChangedFields(department, fromDatabase);
            return departmentDAO.update(department, em);
        });
    }

    private static Department updateChangedFields(Department department, Department fromDatabase) {

        if (department.getEmployee() != null && !department.getEmployee().getEmployeeId().equals(fromDatabase.getEmployee().getEmployeeId())) {
            EmployeeDto employeeDto =  EmployeeService.getEmployeeById(department.getEmployee().getEmployeeId());
            Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
            if(employee == null){
                throw new ResourceNotFound("Employee not found to be a manager" , "ERR1");
            }
            fromDatabase.setEmployee(employee);
        }
        if (department.getDepartmentName() != null && !department.getDepartmentName().equals(fromDatabase.getDepartmentName())) {
            fromDatabase.setDepartmentName(department.getDepartmentName());
        }
        if (department.getIsHead() != null && !department.getIsHead().equals(fromDatabase.getIsHead())) {
            fromDatabase.setIsHead(department.getIsHead());
        }
        return fromDatabase;
    }


    public static Set<EmployeeDto> getAllEmployeesByDepartment(int id) {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Set<Employee> employees = departmentDAO.getAllEmployeesByDepartment(id, em);
            return EmployeeMapper.INSTANCE.toEmployeeDtoList(employees);
        });
    }

    public static boolean deleteDepartment(int id) {
       return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            return departmentDAO.DeleteDepartmentById(id, em);
        });
    }

    public static List<EmployeeDto> getAllManager() {
        return Database.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            List<Employee> employees = departmentDAO.getAllManager(em);
            System.out.println("Manager size: "+employees.size());
            for (Employee employee : employees) {
                System.out.println("Manager: "+employee.getFirstName());
            }
            return EmployeeMapper.INSTANCE.toEmployeeDtoList(employees);
        });
    }
}
