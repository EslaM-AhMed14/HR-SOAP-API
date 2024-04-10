package com.example.persistence.dao.impl;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.interfaces.DepartmentDAOInt;
import com.example.persistence.entity.Department;
import com.example.persistence.entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DepartmentDAO implements DepartmentDAOInt {

    @Override
    public boolean save(Department department, EntityManager em)  throws RuntimeException{
        Department department1 = getDepartmentByName(department.getDepartmentName(), em);
        if (department1 != null) {
            throw new ResourceNotFound("Department already exists", "ERR2");
        }
        em.persist(department);
        Department department2 = em.find(Department.class, department.getDepartmentId());
        if (department2 == null) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Department> get(Integer id, EntityManager em) {
        Department department = em.find(Department.class, id);
        return Optional.ofNullable(department);

    }

    @Override
    public boolean update(Department department, EntityManager em) {
        try {
            em.merge(department);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void delete(Department department, EntityManager em) {
            em.remove(department);
    }

    @Override
    public List<Department> getAllDepartments(EntityManager em) {
      return  em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    @Override
    public boolean DeleteDepartmentById(Integer id, EntityManager em) {
        try {
            Department department = em.find(Department.class, id);
            Set<Employee> employees = department.getEmployees();
            em.remove(department);
            employees.forEach(employee -> {
                employee.setDepartment(null);
                em.merge(employee);
            });
            return true;
        }catch (Exception e) {
            return false;
        }


    }

    @Override
    public  Department getDepartmentByName(String name, EntityManager em) {
        try {
            return em.createQuery("SELECT d FROM Department d WHERE LOWER( d.departmentName) = LOWER(:name)", Department.class)
                    .setParameter("name", name)
                    .getSingleResult();

        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean isManager(Integer id, EntityManager em) {
        List<Department> department = getAllDepartments(em);

       return department.stream().filter(department1 -> department1.getEmployee().getEmployeeId().equals(id))
                .findFirst().orElse(null) != null;
    }


    public Set<Employee> getAllEmployeesByDepartment(int id, EntityManager em) {
        Department department = em.find(Department.class, id);

        return department.getEmployees();
    }

   public List<Employee> getAllManager(EntityManager em) {
       try {
           return em.createQuery("SELECT e FROM Employee e WHERE e.employeeId = e.department.employee.employeeId", Employee.class).getResultList();
       } catch (Exception e) {
           throw new ResourceNotFound("No Manager Found" , "ERR2");
       }
   }

    public boolean isHeadOfDepartment(Integer id, EntityManager em) {
        List<Department> departments = getAllDepartments(em);
        return departments.stream().anyMatch(department -> department.getEmployee() != null && department.getEmployee().getEmployeeId().equals(id) && department.getIsHead());
    }
}
