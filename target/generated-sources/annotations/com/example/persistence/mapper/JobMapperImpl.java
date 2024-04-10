package com.example.persistence.mapper;

import com.example.persistence.dto.JobDto;
import com.example.persistence.entity.Job;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:30+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto jobToJobDto(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDto jobDto = new JobDto();

        jobDto.setJobId( job.getJobId() );
        jobDto.setDepartmentProviderName( job.getDepartmentProviderName() );
        jobDto.setDepartmentProviderId( job.getDepartmentProviderId() );
        jobDto.setJobName( job.getJobName() );
        jobDto.setRequirements( job.getRequirements() );

        return jobDto;
    }

    @Override
    public Job jobDtoToJob(JobDto jobDto) {
        if ( jobDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setJobId( jobDto.getJobId() );
        job.setJobName( jobDto.getJobName() );
        job.setRequirements( jobDto.getRequirements() );

        return job;
    }

    @Override
    public List<JobDto> toJobDtoList(List<Job> jobs) {
        if ( jobs == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( jobs.size() );
        for ( Job job : jobs ) {
            list.add( jobToJobDto( job ) );
        }

        return list;
    }

    @Override
    public List<Job> toJobList(List<JobDto> jobDtos) {
        if ( jobDtos == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( jobDtos.size() );
        for ( JobDto jobDto : jobDtos ) {
            list.add( jobDtoToJob( jobDto ) );
        }

        return list;
    }
}
