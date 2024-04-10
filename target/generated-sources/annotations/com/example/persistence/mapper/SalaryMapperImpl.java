package com.example.persistence.mapper;

import com.example.persistence.dto.SalaryDto;
import com.example.persistence.entity.Employee;
import com.example.persistence.entity.Salary;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:30+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class SalaryMapperImpl implements SalaryMapper {

    @Override
    public SalaryDto salaryToSalaryDto(Salary salary) {
        if ( salary == null ) {
            return null;
        }

        SalaryDto salaryDto = new SalaryDto();

        salaryDto.setEmployeeName( salaryEmployeeFirstName( salary ) );
        salaryDto.setSalaryId( salary.getSalaryId() );
        salaryDto.setBasicSalary( salary.getBasicSalary() );
        salaryDto.setBonus( salary.getBonus() );
        salaryDto.setDeductions( salary.getDeductions() );
        salaryDto.setNetSalary( salary.getNetSalary() );

        return salaryDto;
    }

    @Override
    public Salary salaryDtoToSalary(SalaryDto salaryDto) {
        if ( salaryDto == null ) {
            return null;
        }

        Salary salary = new Salary();

        salary.setEmployee( salaryDtoToEmployee( salaryDto ) );
        salary.setSalaryId( salaryDto.getSalaryId() );
        salary.setBasicSalary( salaryDto.getBasicSalary() );
        salary.setBonus( salaryDto.getBonus() );
        salary.setDeductions( salaryDto.getDeductions() );
        salary.setNetSalary( salaryDto.getNetSalary() );

        return salary;
    }

    @Override
    public List<Salary> toSalaryList(List<SalaryDto> salaryDtos) {
        if ( salaryDtos == null ) {
            return null;
        }

        List<Salary> list = new ArrayList<Salary>( salaryDtos.size() );
        for ( SalaryDto salaryDto : salaryDtos ) {
            list.add( salaryDtoToSalary( salaryDto ) );
        }

        return list;
    }

    @Override
    public List<SalaryDto> toSalaryDtoList(List<Salary> salaries) {
        if ( salaries == null ) {
            return null;
        }

        List<SalaryDto> list = new ArrayList<SalaryDto>( salaries.size() );
        for ( Salary salary : salaries ) {
            list.add( salaryToSalaryDto( salary ) );
        }

        return list;
    }

    private String salaryEmployeeFirstName(Salary salary) {
        if ( salary == null ) {
            return null;
        }
        Employee employee = salary.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    protected Employee salaryDtoToEmployee(SalaryDto salaryDto) {
        if ( salaryDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( salaryDto.getEmployeeName() );

        return employee;
    }
}
