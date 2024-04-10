package com.example.persistence.mappers;

import com.example.persistence.DTOs.EmployeeDto;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.Salary;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:30+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setDepartment( employeeDtoToDepartment( employeeDto ) );
        employee.setSalary( employeeDtoToSalary( employeeDto ) );
        employee.setEmployeeId( employeeDto.getEmployeeId() );
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setJobTitle( employeeDto.getJobTitle() );
        if ( employeeDto.getDateOfBirth() != null ) {
            employee.setDateOfBirth( new Date( employeeDto.getDateOfBirth().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }
        employee.setGender( employeeDto.getGender() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setPhoneNumber( employeeDto.getPhoneNumber() );
        if ( employeeDto.getHireDate() != null ) {
            employee.setHireDate( new Date( employeeDto.getHireDate().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }

        return employee;
    }

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setDepartmentName( employeeDepartmentDepartmentName( employee ) );
        employeeDto.setDepartmentId( employeeDepartmentDepartmentId( employee ) );
        employeeDto.setBasicSalary( employeeSalaryBasicSalary( employee ) );
        employeeDto.setEmployeeId( employee.getEmployeeId() );
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setDateOfBirth( map( employee.getDateOfBirth() ) );
        employeeDto.setHireDate( map( employee.getHireDate() ) );
        employeeDto.setGender( employee.getGender() );
        employeeDto.setJobTitle( employee.getJobTitle() );
        employeeDto.setEmail( employee.getEmail() );
        employeeDto.setPhoneNumber( employee.getPhoneNumber() );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> toEmployeeDtoList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( employeeToEmployeeDto( employee ) );
        }

        return list;
    }

    @Override
    public List<Employee> toEmployeeList(List<EmployeeDto> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeDtos.size() );
        for ( EmployeeDto employeeDto : employeeDtos ) {
            list.add( employeeDtoToEmployee( employeeDto ) );
        }

        return list;
    }

    @Override
    public Set<EmployeeDto> toEmployeeDtoList(Set<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        Set<EmployeeDto> set = new HashSet<EmployeeDto>( Math.max( (int) ( employees.size() / .75f ) + 1, 16 ) );
        for ( Employee employee : employees ) {
            set.add( employeeToEmployeeDto( employee ) );
        }

        return set;
    }

    @Override
    public Set<Employee> toEmployeeList(Set<EmployeeDto> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        Set<Employee> set = new HashSet<Employee>( Math.max( (int) ( employeeDtos.size() / .75f ) + 1, 16 ) );
        for ( EmployeeDto employeeDto : employeeDtos ) {
            set.add( employeeDtoToEmployee( employeeDto ) );
        }

        return set;
    }

    protected Department employeeDtoToDepartment(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentName( employeeDto.getDepartmentName() );
        department.setDepartmentId( employeeDto.getDepartmentId() );

        return department;
    }

    protected Salary employeeDtoToSalary(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Salary salary = new Salary();

        salary.setBasicSalary( employeeDto.getBasicSalary() );

        return salary;
    }

    private String employeeDepartmentDepartmentName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        String departmentName = department.getDepartmentName();
        if ( departmentName == null ) {
            return null;
        }
        return departmentName;
    }

    private Integer employeeDepartmentDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Integer departmentId = department.getDepartmentId();
        if ( departmentId == null ) {
            return null;
        }
        return departmentId;
    }

    private BigDecimal employeeSalaryBasicSalary(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Salary salary = employee.getSalary();
        if ( salary == null ) {
            return null;
        }
        BigDecimal basicSalary = salary.getBasicSalary();
        if ( basicSalary == null ) {
            return null;
        }
        return basicSalary;
    }
}
