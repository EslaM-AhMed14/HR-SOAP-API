package com.example.persistence.dao.impl;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.interfaces.JobDAOInt;
import com.example.persistence.entity.Job;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JobDAO  implements JobDAOInt {


    public List<Job> getAllJobs(EntityManager em) {
        try {
            return em.createQuery("FROM Job", Job.class)
                    .getResultList();
        } catch (Exception e) {
            throw new ResourceNotFound("No Jobs Found" , "ERR2");
        }
    }

    @Override
    public boolean save(Job job, EntityManager em) {
        try {
            em.persist(job);
            return true;
        } catch (Exception e) {
            throw new ResourceNotFound("Job not Created" , "ERR2");
        }
    }

    @Override
    public Optional<Job> get(Integer id, EntityManager em) {
        try {
            return Optional.ofNullable(em.find(Job.class, id));
        } catch (Exception e) {
            throw new ResourceNotFound("Job not Found" , "ERR2");
        }
    }

    @Override
    public boolean update(Job job, EntityManager em) {
        try {
            em.merge(job);
            return true;
        } catch (Exception e) {
            throw new ResourceNotFound("Job not Updated" , "ERR2");
        }
    }

    @Override
    public void delete(Job job, EntityManager em) {
        try {
            em.remove(job);
        } catch (Exception e) {
            throw new ResourceNotFound("Job not Deleted" , "ERR2");
        }

    }

    public List<Job> getJobOpeningForDepartment(int departmentId, EntityManager em) {
        try {
            return em.createQuery("FROM Job WHERE department.departmentId = :departmentId", Job.class)
                    .setParameter("departmentId", departmentId)
                    .getResultList();
        } catch (Exception e) {
            throw new ResourceNotFound("No Jobs Found" , "ERR2");
        }
    }
}
