package com.example.persistence.dao.impl;


import com.example.exception.ResourceNotFound;
import com.example.exception.UnlegalArgument;
import com.example.persistence.dao.interfaces.PerformanceReviewDAOInt;
import com.example.persistence.entity.PerformanceReview;
import jakarta.json.bind.JsonbException;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class PerformanceReviewDAO  implements PerformanceReviewDAOInt {



    @Override
    public boolean save(PerformanceReview performanceReview, EntityManager em) {
         em.persist(performanceReview);
        return true;
    }

    @Override
    public Optional<PerformanceReview> get(Integer id, EntityManager em) {
        return Optional.empty();
    }

    @Override
    public boolean update(PerformanceReview performanceReview, EntityManager em) {
         em.merge(performanceReview);
        return true;
    }

    @Override
    public void delete(PerformanceReview performanceReview, EntityManager em) {
                em.remove(performanceReview);
    }


    public List<PerformanceReview> getAllPerformanceReview(EntityManager em) {
        try{
            return em.createQuery("from  PerformanceReview p", PerformanceReview.class)
                    .getResultList();
        }catch (Exception e) {
            e.getMessage();
            throw new ResourceNotFound("No Performance Review Found");
        }

    }

    public void addPerformanceReview(PerformanceReview performanceReview, EntityManager em) {
        try {
            performanceReview.setReviewDate(new java.sql.Date(new java.util.Date().getTime()));
            save(performanceReview , em);

        }catch (JsonbException e){
            throw new UnlegalArgument("Invalid Performance Rating value. Allowed values are: Excellent, Very_good, Good, Fair, Poor" , "ERR1");

        } catch (Exception e) {
                throw new ResourceNotFound("Performance Review not added" , "ERR1");
        }
    }

    public List<PerformanceReview> getEmployeesByPage(int pageId, EntityManager em) {
        try {
            return em.createQuery("SELECT p FROM PerformanceReview p", PerformanceReview.class)
                    .setFirstResult((pageId-1) * 2)
                    .setMaxResults(2)
                    .getResultList();
        } catch (Exception e) {

            throw new ResourceNotFound("No Performance Review found" , "ERR1"   );
        }
    }
}
