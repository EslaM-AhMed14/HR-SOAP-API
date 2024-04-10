package com.example.persistence.mapper;

import com.example.persistence.dto.SalaryDto;
import com.example.persistence.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalaryMapper {
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    @Mapping(source = "employee.firstName", target = "employeeName")
    SalaryDto salaryToSalaryDto(Salary salary);

    @Mapping(source = "employeeName", target = "employee.firstName")
    Salary salaryDtoToSalary(SalaryDto salaryDto);

    List<Salary> toSalaryList(List<SalaryDto> salaryDtos);

    List<SalaryDto> toSalaryDtoList(List<Salary> salaries);
}
