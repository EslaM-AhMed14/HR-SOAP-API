package com.example.persistence.service;

import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.impl.DepartmentDAO;
import com.example.persistence.dao.impl.EmployeeDAO;
import com.example.persistence.dto.DepartmentDto;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entity.Department;
import com.example.persistence.entity.Employee;
import com.example.persistence.entity.Salary;
import com.example.persistence.mapper.DepartmentMapper;
import com.example.persistence.mapper.EmployeeMapper;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Optional;

public class EmployeeService {


    private EmployeeService() {
    }

    public static List<EmployeeDto> getAllEmployees() {
      return Database.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.getAllEmployees(em);
            return EmployeeMapper.INSTANCE.toEmployeeDtoList(employees);
        });
    }

    public static EmployeeDto getEmployeeById(int id) {
        return Database.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Optional<Employee> employee = employeeDAO.get(id, em);
            if (employee.isEmpty()) {
                return null;
            }
            Employee employee1 = employee.get();
            return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employee1);
        });
    }

    public static boolean addEmployee(EmployeeDto employeeDto) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("DB");
        jakarta.persistence.EntityManager em = emf.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAO();

            Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
            boolean done =  employeeDAO.save(employee, em);
            em.close();
            return done;
    }

    public static boolean deleteEmployee(int id) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("DB");
        jakarta.persistence.EntityManager em = emf.createEntityManager();
        EmployeeDAO employeeDAO = new EmployeeDAO();
            Optional<Employee> employee = employeeDAO.delete(id, em);
            if (employee.isEmpty()) {
                return false;
            }
            em.close();
            return true;


    }

    public static boolean updateEmployee(EmployeeDto employeeDto) {
           EntityManagerFactory emf =
                   Persistence.createEntityManagerFactory("DB");
           jakarta.persistence.EntityManager emm = emf.createEntityManager();
           jakarta.persistence.EntityManager em = emf.createEntityManager();

            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employeeDto);
            Employee fromDatabase = employeeDAO.get(employee.getEmployeeId(), emm).orElse(null);
            emm.close();
            if (fromDatabase == null) {
                throw  new ResourceNotFound("Employee not found" , "ERR1");
            }
            employee = updateChangedFields(employee, fromDatabase);
            return  employeeDAO.update(employee, em);

    }

    public static Employee updateChangedFields(Employee employee, Employee fromDatabase) {
        if (employee.getFirstName() != null && !employee.getFirstName().equals(fromDatabase.getFirstName())) {
            fromDatabase.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null && !employee.getLastName().equals(fromDatabase.getLastName())) {
            fromDatabase.setLastName(employee.getLastName());
        }
        if (employee.getDateOfBirth() != null && !employee.getDateOfBirth().equals(fromDatabase.getDateOfBirth())) {
            fromDatabase.setDateOfBirth(employee.getDateOfBirth());
        }
        if (employee.getGender() != null && !employee.getGender().equals(fromDatabase.getGender())) {
            fromDatabase.setGender(employee.getGender());
        }
        if (employee.getEmail() != null && !employee.getEmail().equals(fromDatabase.getEmail())) {
            fromDatabase.setEmail(employee.getEmail());
        }
        if (employee.getPhoneNumber() != null && !employee.getPhoneNumber().equals(fromDatabase.getPhoneNumber())) {
            fromDatabase.setPhoneNumber(employee.getPhoneNumber());
        }
        if (employee.getDepartment().getDepartmentId() != null && !employee.getDepartment().getDepartmentId().equals(fromDatabase.getDepartment().getDepartmentId())) {
            DepartmentDto department =  DepartmentService.getDepartmentById(employee.getDepartment().getDepartmentId());
            Department department1 = DepartmentMapper.INSTANCE.departmentDtoToDepartment(department);
            fromDatabase.setDepartment(department1);
        }
        if (employee.getSalary().getBasicSalary() != null && !employee.getSalary().getBasicSalary().equals(fromDatabase.getSalary().getBasicSalary())) {
            Salary salary = fromDatabase.getSalary();
            salary.setBasicSalary(employee.getSalary().getBasicSalary());
            fromDatabase.setSalary(salary);
        }
        return fromDatabase;
    }

    public static boolean updateEmployeeDepartment(Integer employeeId, Integer departmentId) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("DB");
        jakarta.persistence.EntityManager em = emf.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Employee employee = employeeDAO.get(employeeId, em).orElse(null);
            Department department = departmentDAO.get(departmentId, em).orElse(null);
            if (employee == null || department == null) {
                return false;
            }
            employee.setDepartment(department);
            return employeeDAO.update(employee, em);

    }

    public static List<EmployeeDto> getEmployeesByPage(int pageId) {
        return Database.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.getEmployeesByPage(pageId, em);
            return EmployeeMapper.INSTANCE.toEmployeeDtoList(employees);
        });
    }
}
