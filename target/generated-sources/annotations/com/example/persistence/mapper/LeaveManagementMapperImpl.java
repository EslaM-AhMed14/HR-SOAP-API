package com.example.persistence.mapper;

import com.example.persistence.dto.LeaveManagementDto;
import com.example.persistence.entity.Employee;
import com.example.persistence.entity.LeaveManagement;
import java.sql.Date;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:30+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class LeaveManagementMapperImpl implements LeaveManagementMapper {

    @Override
    public LeaveManagementDto leaveManagementToLeaveManagementDto(LeaveManagement leaveManagement) {
        if ( leaveManagement == null ) {
            return null;
        }

        LeaveManagementDto leaveManagementDto = new LeaveManagementDto();

        leaveManagementDto.setEmployeeName( leaveManagementEmployeeFirstName( leaveManagement ) );
        leaveManagementDto.setEmployeeId( leaveManagementEmployeeEmployeeId( leaveManagement ) );
        leaveManagementDto.setLeaveId( leaveManagement.getLeaveId() );
        leaveManagementDto.setLeaveType( leaveManagement.getLeaveType() );
        leaveManagementDto.setStartDate( map( leaveManagement.getStartDate() ) );
        leaveManagementDto.setEndDate( map( leaveManagement.getEndDate() ) );
        leaveManagementDto.setStatus( leaveManagement.getStatus() );
        leaveManagementDto.setAccreditationDate( map( leaveManagement.getAccreditationDate() ) );

        return leaveManagementDto;
    }

    @Override
    public LeaveManagement leaveManagementDtoToLeaveManagement(LeaveManagementDto leaveManagementDto) {
        if ( leaveManagementDto == null ) {
            return null;
        }

        LeaveManagement leaveManagement = new LeaveManagement();

        leaveManagement.setEmployee( leaveManagementDtoToEmployee( leaveManagementDto ) );
        leaveManagement.setLeaveId( leaveManagementDto.getLeaveId() );
        leaveManagement.setLeaveType( leaveManagementDto.getLeaveType() );
        if ( leaveManagementDto.getStartDate() != null ) {
            leaveManagement.setStartDate( new Date( leaveManagementDto.getStartDate().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }
        if ( leaveManagementDto.getEndDate() != null ) {
            leaveManagement.setEndDate( new Date( leaveManagementDto.getEndDate().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }
        leaveManagement.setStatus( leaveManagementDto.getStatus() );
        if ( leaveManagementDto.getAccreditationDate() != null ) {
            leaveManagement.setAccreditationDate( new Date( leaveManagementDto.getAccreditationDate().atStartOfDay( ZoneOffset.UTC ).toInstant().toEpochMilli() ) );
        }

        return leaveManagement;
    }

    @Override
    public List<LeaveManagement> toLeaveManagementList(List<LeaveManagementDto> leaveManagementDtos) {
        if ( leaveManagementDtos == null ) {
            return null;
        }

        List<LeaveManagement> list = new ArrayList<LeaveManagement>( leaveManagementDtos.size() );
        for ( LeaveManagementDto leaveManagementDto : leaveManagementDtos ) {
            list.add( leaveManagementDtoToLeaveManagement( leaveManagementDto ) );
        }

        return list;
    }

    @Override
    public List<LeaveManagementDto> toLeaveManagementDtoList(List<LeaveManagement> leaveManagements) {
        if ( leaveManagements == null ) {
            return null;
        }

        List<LeaveManagementDto> list = new ArrayList<LeaveManagementDto>( leaveManagements.size() );
        for ( LeaveManagement leaveManagement : leaveManagements ) {
            list.add( leaveManagementToLeaveManagementDto( leaveManagement ) );
        }

        return list;
    }

    private String leaveManagementEmployeeFirstName(LeaveManagement leaveManagement) {
        if ( leaveManagement == null ) {
            return null;
        }
        Employee employee = leaveManagement.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private Integer leaveManagementEmployeeEmployeeId(LeaveManagement leaveManagement) {
        if ( leaveManagement == null ) {
            return null;
        }
        Employee employee = leaveManagement.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer employeeId = employee.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }

    protected Employee leaveManagementDtoToEmployee(LeaveManagementDto leaveManagementDto) {
        if ( leaveManagementDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( leaveManagementDto.getEmployeeName() );
        employee.setEmployeeId( leaveManagementDto.getEmployeeId() );

        return employee;
    }
}
