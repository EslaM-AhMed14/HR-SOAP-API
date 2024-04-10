package com.example.persistence.dao.interfaces;

import com.example.persistence.entity.Department;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface DepartmentDAOInt extends DAO<Department> {

    List<Department> getAllDepartments(EntityManager em);

    boolean  DeleteDepartmentById(Integer id, EntityManager em);

    Department getDepartmentByName(String name, EntityManager em);
    public boolean isManager(Integer id, EntityManager em);
}
