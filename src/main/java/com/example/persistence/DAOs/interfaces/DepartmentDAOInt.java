package com.example.persistence.DAOs.interfaces;

import com.example.persistence.entities.Department;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface DepartmentDAOInt extends DAO<Department> {

    List<Department> getAllDepartments(EntityManager em);

    boolean  DeleteDepartmentById(Integer id, EntityManager em);

    Department getDepartmentByName(String name, EntityManager em);
    public boolean isManager(Integer id, EntityManager em);
}
