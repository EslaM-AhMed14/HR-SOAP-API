package com.example.persistence.mappers;

import com.example.persistence.DTOs.DepartmentDto;
import com.example.persistence.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "employee.firstName", target = "managerName")
    @Mapping(source = "employee.employeeId", target =  "managerId")
    @Mapping( source = "employee.jobTitle", target= "managerJobTitle")
    DepartmentDto departmentToDepartmentDto(Department department);

    @Mapping(source = "managerName", target = "employee.firstName")
    @Mapping(source = "managerId", target = "employee.employeeId")
    @Mapping(source = "managerJobTitle", target = "employee.jobTitle")
    Department departmentDtoToDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> toDepartmentDtoList(List<Department> departments);

    List<Department> toDepartmentList(List<DepartmentDto> departmentDtos);
}