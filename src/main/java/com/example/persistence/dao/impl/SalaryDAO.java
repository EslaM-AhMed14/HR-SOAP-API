package com.example.persistence.dao.impl;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.interfaces.SalaryDAOInt;
import com.example.persistence.entity.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SalaryDAO  implements SalaryDAOInt {


    @Override
    public boolean save(Salary salary, EntityManager em) {
        em.persist(salary);
        Salary salary1 = em.find(Salary.class, salary.getSalaryId());
        return salary1 == null;
    }

    @Override
    public Optional<Salary> get(Integer id, EntityManager em) {
        return Optional.empty();
    }

    @Override
    public boolean update(Salary salary, EntityManager em) {


        Salary salary1 = em.find(Salary.class, salary.getSalaryId());
        if (salary1 == null) {
            throw new ResourceNotFound("No salary found with id: " + salary.getSalaryId() , "ERR1");
        }
        em.merge(salary);
        return true;


    }

    @Override
    public void delete(Salary salary, EntityManager em) {
            em.remove(salary);
    }

//    public Salary getSalaryByEmployeeId(Integer employeeId, EntityManager em) {
//        return em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
//                .setParameter("employeeId", employeeId)
//                .getSingleResult();
//    }

    public Salary getBasicSalary(Integer employeeId, EntityManager em) {
       try {
           return  em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                   .setParameter("employeeId", employeeId)
                   .getSingleResult();
       }catch (NoResultException e){
           throw new ResourceNotFound("No salary found for employee with id: " + employeeId , "ERR1");
       }catch (NonUniqueResultException e){
           throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId);
       }

    }

    public List<Salary> getBasicSalaries(EntityManager em) {
        try {
            return em.createQuery("SELECT s FROM Salary s", Salary.class)
                    .getResultList();
        }catch (NoResultException e){
            throw new ResourceNotFound("No salaries found" , "ERR1");
        }
    }

    public List<Salary> getBasicSalaryRange(int min, int max, EntityManager em) {
        try {
            return em.createQuery("SELECT s FROM Salary s WHERE s.basicSalary BETWEEN :min AND :max", Salary.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        }catch (NoResultException e){
            throw new ResourceNotFound("No salaries found in the range: " + min + " - " + max , "ERR1");
        }
    }

    public void applayBonusForAllEmployees(double percentage, EntityManager em) {
        try {
            List<Salary> salaries = em.createQuery("SELECT s FROM Salary s", Salary.class).getResultList();

            for (Salary salary : salaries) {
                BigDecimal newBonus = salary.getBasicSalary().multiply(BigDecimal.valueOf(percentage));
                if (newBonus.compareTo(BigDecimal.valueOf(1000)) > 0) {
                    salary.setBonus(BigDecimal.valueOf(1000));
                } else {
                    salary.setBonus(newBonus);
                }
                em.merge(salary);
            }

        } catch (Exception e) {
            throw new ResourceNotFound("Failed to apply bonus", "ERR1");
        }
    }

    public void applayDeductionsForAllEmployees(double percentage, EntityManager em) {
        try {
            em.createQuery("UPDATE Salary s SET s.deductions = s.basicSalary * :percentage")
                    .setParameter("percentage", percentage)
                    .executeUpdate();
        } catch (Exception e) {
            throw new ResourceNotFound("No salaries found" , "ERR1");
        }
    }

    public void applayBonusForEmployee(int employeeId, double percentage, EntityManager em) {

        try {
            Salary salary = em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();
            BigDecimal newBonus = salary.getBasicSalary().multiply(BigDecimal.valueOf(percentage));
            if (newBonus.compareTo(BigDecimal.valueOf(1000)) > 0) {
                salary.setBonus(BigDecimal.valueOf(1000));
            } else {
                salary.setBonus(newBonus);
            }
            em.merge(salary);
        } catch (NoResultException e) {
            throw new ResourceNotFound("No salary found for employee with id: " + employeeId , "ERR1");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId);
        }

    }

    public void applayDeductionsForEmployee(int employeeId, double percentage, EntityManager em) {
        try {
            em.createQuery("UPDATE Salary s SET s.deductions = s.basicSalary * :percentage WHERE s.employee.employeeId = :employeeId")
                    .setParameter("percentage", percentage)
                    .setParameter("employeeId", employeeId)
                    .executeUpdate();
        } catch (NoResultException e) {
            throw new ResourceNotFound("No salary found for employee with id: " + employeeId , "ERR1");
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId );
        }
    }


}
