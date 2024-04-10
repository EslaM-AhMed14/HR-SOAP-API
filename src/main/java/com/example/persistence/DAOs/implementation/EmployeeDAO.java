package com.example.persistence.DAOs.implementation;


import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DAOs.interfaces.EmployeeDAOInt;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeDAO implements EmployeeDAOInt {



    @Override
    public boolean save(Employee employee, EntityManager em)  {

        try {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.getDepartmentByName(employee.getDepartment().getDepartmentName(), em);

            if(department == null){
                throw new ResourceNotFound("Department not found" , "ERR1");
            }

            System.out.println("Department "+ employee.getDepartment().getDepartmentName());
            System.out.println("phone"+ employee.getPhoneNumber());
            System.out.println("emsil"+employee.getEmail());

            System.out.println("Department found");
            em.getTransaction().begin();
            employee.setDepartment(department);
            Salary salary = new Salary();
            salary.setBasicSalary(employee.getSalary().getBasicSalary());
            employee.setSalary(salary);
            salary.setEmployee(employee);
            em.persist(employee);
            em.persist(salary);
            em.getTransaction().commit();

            em.getTransaction().begin();
            Employee employee1 = em.find(Employee.class, employee.getEmployeeId());
            em.getTransaction().commit();
            System.out.println("end");

            return !Objects.isNull(employee1);
        }catch (NoResultException e) {
            throw new ResourceNotFound("Failed to add employee: " + e.getMessage() , "ERR4");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFound("the email or phoneNumber is already in use" , "ERR3");
        }

    }

    @Override
    public Optional<Employee> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public boolean update(Employee employee, EntityManager em) {
        try {
            em.getTransaction().begin();

            em.merge(employee);
            em.flush();
            em.getTransaction().commit();
            em.close();
                System.out.println("Employee Updated");
                return true;
        }catch (Exception e) {
            throw new ResourceNotFound("Cant Update"    , "ERR4");
        }

    }

    @Override
    public void delete(Employee employee, EntityManager em) {
        em.remove(employee);
    }

    @Override
    public Optional<Employee> delete(int id, EntityManager em) {

        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        if (Objects.isNull(employee)) {
            return Optional.empty();
        }
        em.remove(employee);
        em.getTransaction().commit();
        return Optional.of(employee);

    }

    @Override
    public List<Employee> getEmployeesByPage(int pageId, EntityManager em) {
        try {
            return em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .setFirstResult((pageId-1) * 2)
                    .setMaxResults(2)
                    .getResultList();
        } catch (Exception e) {
            throw new ResourceNotFound("No Employees found" , "ERR1");
        }
    }

    @Override
    public List<Employee> getAllEmployees(EntityManager em) {

        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }




}
