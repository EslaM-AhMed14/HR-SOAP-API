package com.example.persistence.mapper;


import com.example.persistence.dto.JobDto;
import com.example.persistence.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public  interface  JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

//    @Mapping(source = "department.departmentName", target = "departmentName")
//    @Mapping(source = "department.departmentId", target = "departmentId")
    JobDto jobToJobDto(Job job);


    Job jobDtoToJob(JobDto jobDto);

    List<JobDto> toJobDtoList(List<Job> jobs);

    List<Job> toJobList(List<JobDto> jobDtos);
}
