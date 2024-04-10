package com.example.persistence.DAOs.interfaces;


import jakarta.persistence.EntityManager;
import java.util.Optional;

interface DAO <T> {

    boolean save(T t, EntityManager em);
    Optional<T> get(Integer id, EntityManager em);
    boolean  update(T t, EntityManager em);
    void delete(T t, EntityManager em);
}
