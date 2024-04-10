package com.example.persistence.mapper;

import com.example.persistence.dto.LeaveManagementDto;
import com.example.persistence.entity.LeaveManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Mapper
public interface LeaveManagementMapper {
    LeaveManagementMapper INSTANCE = Mappers.getMapper(LeaveManagementMapper.class);

    @Mapping(source = "employee.firstName", target = "employeeName")
    @Mapping(source = "employee.employeeId", target = "employeeId")
    LeaveManagementDto leaveManagementToLeaveManagementDto(LeaveManagement leaveManagement);

    @Mapping(source = "employeeName", target = "employee.firstName")
    @Mapping(source = "employeeId", target = "employee.employeeId")
    LeaveManagement leaveManagementDtoToLeaveManagement(LeaveManagementDto leaveManagementDto);

    List<LeaveManagement> toLeaveManagementList(List<LeaveManagementDto> leaveManagementDtos);

    List<LeaveManagementDto> toLeaveManagementDtoList(List<LeaveManagement> leaveManagements);


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
