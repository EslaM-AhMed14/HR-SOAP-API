package com.example.persistence.DAOs.implementation;

import com.example.CustomException.ResourceNotFound;
import com.example.CustomException.UnlegalArgument;
import com.example.persistence.DAOs.interfaces.LeavManagementDAOInt;
import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.entities.LeaveManagement;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class LeaveManagementDAO  implements LeavManagementDAOInt {


    public List<LeaveManagement> getAllPerformanceReview(EntityManager em) {
        try {
            List<LeaveManagement> list = em.createQuery("FROM LeaveManagement l", LeaveManagement.class)
                    .getResultList();
            System.out.println("List of Leaves: " + list.size());
            return list;
        } catch (Exception e) {
            throw new ResourceNotFound("No Vications Review Found" , "ERR1");
        }
    }



    @Override
    public boolean save(LeaveManagement leaveManagement, EntityManager em) {

            int days = areDatesValid(leaveManagement);
            em.persist(leaveManagement);
            return true;

    }

    @Override
    public Optional<LeaveManagement> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(LeaveManagement.class, id));
    }

    @Override
    public boolean update(LeaveManagement leaveManagement, EntityManager em) {
        return false;
    }

    @Override
    public void delete(LeaveManagement leaveManagement, EntityManager em) {

    }

    public int areDatesValid(LeaveManagement leaveManagement) {
        Date startDate = leaveManagement.getStartDate();
        Date endDate = leaveManagement.getEndDate();


        if (startDate == null ) {
            throw new ResourceNotFound("Cannot have null start date" , "ERR1");
        }
        if(endDate == null){
            throw new UnlegalArgument("Cannot have null end date" , "ERR2");
        }

        LocalDate startLocalDate;
        LocalDate endLocalDate;
        try {
             startLocalDate = startDate.toLocalDate();
             endLocalDate = endDate.toLocalDate();

        }catch (Exception e) {
            e.printStackTrace();
            throw new UnlegalArgument("Invalid date format" , "ERR2");
        }



        if (endLocalDate.isBefore(startLocalDate)) {

            throw new UnlegalArgument("End date cannot be before start date" , "ERR2");
        }


        Period period = Period.between(startLocalDate, endLocalDate);

        int days = period.getDays();


        if(days >= 30) {
            throw new ResourceNotFound("Cannot have more than 30 days of leave", "ERR1");
        }

        return days;
    }


}
