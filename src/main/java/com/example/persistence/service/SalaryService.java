package com.example.persistence.service;

import com.example.persistence.dao.impl.SalaryDAO;
import com.example.persistence.dto.SalaryDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entity.Salary;
import com.example.persistence.mapper.SalaryMapper;

import java.util.List;

public class SalaryService {
    private SalaryService() {
    }

    public static SalaryDto getBasicSalary(int employeeId) {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.getBasicSalary(employeeId, em);
            return SalaryMapper.INSTANCE.salaryToSalaryDto(salary);
        });
    }

    public static List<SalaryDto> getBasicSalaries() {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.getBasicSalaries(em);
            return SalaryMapper.INSTANCE.toSalaryDtoList(salaries);
        });
    }

    public static List<SalaryDto> getBasicSalaryRange(int min, int max) {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.getBasicSalaryRange(min, max, em);
            return SalaryMapper.INSTANCE.toSalaryDtoList(salaries);
        });
    }


    public static boolean updateSalary(SalaryDto salaryDto) {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = SalaryMapper.INSTANCE.salaryDtoToSalary(salaryDto);
            return salaryDAO.update(salary, em);
        });
    }

    public static void applayBonusForAllEmployees(double percentage) {
        Database.doInTransactionWithoutResult(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            salaryDAO.applayBonusForAllEmployees(percentage, em);
        });
    }

    public static void applayDeductionsForAllEmployees(double percentage) {
        Database.doInTransactionWithoutResult(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            salaryDAO.applayDeductionsForAllEmployees(percentage, em);
        });
    }

    public static void applayBonusForEmployee(int employeeId, double percentage) {
        Database.doInTransactionWithoutResult(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            salaryDAO.applayBonusForEmployee(employeeId, percentage, em);
        });
    }

    public static void applayDeductionsForEmployee(int employeeId, double percentage) {
        Database.doInTransactionWithoutResult(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            salaryDAO.applayDeductionsForEmployee(employeeId, percentage, em);
        });
    }


}
