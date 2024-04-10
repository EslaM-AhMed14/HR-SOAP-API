package com.example.persistence.dao.interfaces;

import com.example.persistence.entity.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAOInt  extends  DAO<Employee>{

    List<Employee> getAllEmployees(EntityManager em);

    public Optional<Employee> delete(int id, EntityManager em) ;

    List<Employee> getEmployeesByPage(int pageId, EntityManager em);
}
