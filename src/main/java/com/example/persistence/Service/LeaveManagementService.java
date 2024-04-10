package com.example.persistence.Service;

import com.example.CustomException.ResourceNotFound;
import com.example.CustomException.UnlegalArgument;
import com.example.persistence.DAOs.implementation.DepartmentDAO;
import com.example.persistence.DAOs.implementation.EmployeeDAO;
import com.example.persistence.DAOs.implementation.LeaveManagementDAO;

import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.LeaveManagement;
import com.example.persistence.enums.LeaveStatus;
import com.example.persistence.mappers.LeaveManagementMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LeaveManagementService {
    public static List<LeaveManagementDto> getAllLeaves() {
        return Database.doInTransaction(em->{
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
           List <LeaveManagement> leaves =  leaveDAO.getAllPerformanceReview(em);

            return LeaveManagementMapper.INSTANCE.toLeaveManagementDtoList(leaves);
        });
    }

    public static void createLeave(LeaveManagementDto performanceReviewDto) {
        Database.doInTransactionWithoutResult(em -> {
            System.out.println("service Leave");
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("DB");
            EntityManager em1 = emf.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.get(performanceReviewDto.getEmployeeId(), em1).orElse(null);
            if (employee == null) {
                throw new ResourceNotFound("Employee not found" , "ERR1");
            }
            System.out.println("Employee found");
            em1.close();
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = LeaveManagementMapper.INSTANCE.leaveManagementDtoToLeaveManagement(performanceReviewDto);
            leave.setEmployee(employee);

            leave.setStatus(LeaveStatus.Pending);
            System.out.println("Leave set to pending");

            leaveDAO.save(leave, em);
            System.out.println("Leave created");
        });
    }


    public static void approveLeave(Integer leaveId, Integer managerId) {
        Database.doInTransactionWithoutResult(em -> {
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = leaveDAO.get(leaveId, em).orElse(null);
            if (leave == null) {
                throw new ResourceNotFound("Leave not found" , "ERR1");
            }
            if (leave.getStatus() != LeaveStatus.Pending) {
                throw new ResourceNotFound("Leave is not pending, leave is already "+ leave.getStatus() +" From " + leave.getAccreditationDate());
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();

            List<Employee> managers = departmentDAO.getAllManager(em);

            Employee manager = managers.stream().filter(m -> m.getEmployeeId().equals(managerId)).findFirst().orElse(null);

            if (manager == null) {
                throw new UnlegalArgument("You Are not the manager, the Head Only can Reject the leave or the Direct Manager" , "ERR5");
            }


            if( !manager.getDepartment().equals(leave.getEmployee().getDepartment())){
                if(!manager.getDepartment().getIsHead())
                    throw new UnlegalArgument("You Are Not Direct Manager ,the Head Only can Acesept the leave and the Direct Manager" , "ERR5");
            }

            leave.setStatus(LeaveStatus.Approved);

            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);
            leave.setAccreditationDate(sqlDate);

            leaveDAO.save(leave, em);
        });
    }

    public static void rejectLeave(Integer leaveId, Integer managerId) {
        Database.doInTransactionWithoutResult(em -> {
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = leaveDAO.get(leaveId, em).orElse(null);
            if (leave == null) {
                throw new ResourceNotFound("Leave not found" , "ERR1");
            }
            if (leave.getStatus() != LeaveStatus.Pending) {
                throw new ResourceNotFound("Leave is not pending, leave is already "+ leave.getStatus() +" From " + leave.getAccreditationDate() , "ERR3");
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();

            List<Employee> managers = departmentDAO.getAllManager(em);

            Employee manager = managers.stream().filter(m -> m.getEmployeeId().equals(managerId)).findFirst().orElse(null);

            if (manager == null) {
                throw new UnlegalArgument("You Are not the manager, the Head Only can Reject the leave or the Direct Manager" , "ERR5");
            }


            if( !manager.getDepartment().equals(leave.getEmployee().getDepartment())){
                if(!manager.getDepartment().getIsHead())
                    throw new UnlegalArgument("You Are Not Direct Manager or The Head ,the Head Only can Reject the leave and the Direct Manager" , "ERR5");
            }

            leave.setStatus(LeaveStatus.Rejected);

            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);
            leave.setAccreditationDate(sqlDate);

            leaveDAO.save(leave, em);
        });
    }
}
