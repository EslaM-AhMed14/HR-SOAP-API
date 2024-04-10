package com.example.persistence.mapper;

import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.departmentId")
    @Mapping(source = "basicSalary", target = "salary.basicSalary")
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "department.departmentId", target = "departmentId")
    @Mapping(source = "salary.basicSalary", target = "basicSalary")
    EmployeeDto employeeToEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeeDtoList(List<Employee> employees);

    List<Employee> toEmployeeList(List<EmployeeDto> employeeDtos);

    Set<EmployeeDto> toEmployeeDtoList(Set<Employee> employees);

    Set<Employee> toEmployeeList(Set<EmployeeDto> employeeDtos);
    default LocalDate map(java.util.Date date) {
        if (date instanceof java.sql.Date) {
            date = new java.util.Date(date.getTime());
        }
        return date != null ? date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }

    default java.util.Date map(LocalDate localDate) {
        return localDate != null ? java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }


}
