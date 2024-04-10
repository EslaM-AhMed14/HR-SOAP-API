package com.example.persistence.Service;

import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DAOs.implementation.PerformanceReviewDAO;
import com.example.persistence.DTOs.EmployeeDto;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.PerformanceReview;
import com.example.persistence.mappers.EmployeeMapper;
import com.example.persistence.mappers.PerformanceReviewMapper;

import java.util.List;

public class PerformanceServace {
    public static List<PerformancReviewDto> getAllPerformanceReview() {
        return Database.doInTransaction(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
            List<PerformanceReview>  Reviews = performanceDAO.getAllPerformanceReview(em);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(Reviews);
        });
    }

    public static void  addPerformanceReview(PerformancReviewDto performanceReviewDto) {
         Database.doInTransactionWithoutResult(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
             System.out.println("performance review dto" + performanceReviewDto.toString());
           PerformanceReview performanceReview = PerformanceReviewMapper.INSTANCE
                    .performanceReviewDtoToPerformanceReview(performanceReviewDto);
             System.out.println("preformance"+performanceReview.toString());
           EmployeeDto employee = EmployeeService.getEmployeeById(performanceReviewDto.getReviwerId());
           if(employee == null) {
            throw new ResourceNotFound("the employee does not exist in the database" , "ERR1");
           }
             System.out.println("employee"+employee.toString());
            Employee employee1 = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employee);
                performanceReview.setEmployee(employee1);
             performanceDAO.addPerformanceReview(performanceReview, em);
        });
    }

    public static List<PerformancReviewDto> getEmployeesByPage(int pageId) {
        return Database.doInTransaction(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
            List<PerformanceReview> performanceReviews = performanceDAO.getEmployeesByPage(pageId, em);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviews);
        });
    }
}
