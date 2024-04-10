package com.example.persistence.mapper;

import com.example.persistence.dto.DepartmentDto;
import com.example.persistence.entity.Department;
import com.example.persistence.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:30+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDto departmentToDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setManagerName( departmentEmployeeFirstName( department ) );
        departmentDto.setManagerId( departmentEmployeeEmployeeId( department ) );
        departmentDto.setManagerJobTitle( departmentEmployeeJobTitle( department ) );
        departmentDto.setDepartmentId( department.getDepartmentId() );
        departmentDto.setDepartmentName( department.getDepartmentName() );
        departmentDto.setIsHead( department.getIsHead() );

        return departmentDto;
    }

    @Override
    public Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setEmployee( departmentDtoToEmployee( departmentDto ) );
        department.setDepartmentId( departmentDto.getDepartmentId() );
        department.setDepartmentName( departmentDto.getDepartmentName() );
        department.setIsHead( departmentDto.getIsHead() );

        return department;
    }

    @Override
    public List<DepartmentDto> toDepartmentDtoList(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentDto> list = new ArrayList<DepartmentDto>( departments.size() );
        for ( Department department : departments ) {
            list.add( departmentToDepartmentDto( department ) );
        }

        return list;
    }

    @Override
    public List<Department> toDepartmentList(List<DepartmentDto> departmentDtos) {
        if ( departmentDtos == null ) {
            return null;
        }

        List<Department> list = new ArrayList<Department>( departmentDtos.size() );
        for ( DepartmentDto departmentDto : departmentDtos ) {
            list.add( departmentDtoToDepartment( departmentDto ) );
        }

        return list;
    }

    private String departmentEmployeeFirstName(Department department) {
        if ( department == null ) {
            return null;
        }
        Employee employee = department.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private Integer departmentEmployeeEmployeeId(Department department) {
        if ( department == null ) {
            return null;
        }
        Employee employee = department.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer employeeId = employee.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }

    private String departmentEmployeeJobTitle(Department department) {
        if ( department == null ) {
            return null;
        }
        Employee employee = department.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String jobTitle = employee.getJobTitle();
        if ( jobTitle == null ) {
            return null;
        }
        return jobTitle;
    }

    protected Employee departmentDtoToEmployee(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( departmentDto.getManagerName() );
        employee.setEmployeeId( departmentDto.getManagerId() );
        employee.setJobTitle( departmentDto.getManagerJobTitle() );

        return employee;
    }
}
