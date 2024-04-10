package com.example.persistence.Service;

import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DAOs.implementation.DepartmentDAO;
import com.example.persistence.DAOs.implementation.JobDAO;
import com.example.persistence.DTOs.JobDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Job;
import com.example.persistence.mappers.JobMapper;

import java.util.List;

public class JobService {
    public static List<JobDto> getAllJobOpenings() {
        return Database.doInTransaction(em -> {
            JobDAO jobDAO = new JobDAO();
            List<Job> jobs = jobDAO.getAllJobs(em);
            return JobMapper.INSTANCE.toJobDtoList(jobs);

        });
    }

    public static void createJobOpening(JobDto jobDto) {
        Database.doInTransactionWithoutResult(em -> {
            JobDAO jobDAO = new JobDAO();
            Job job = JobMapper.INSTANCE.jobDtoToJob(jobDto);
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department  department = departmentDAO.get(jobDto.getDepartmentProviderId(), em).orElse(null);
            if (department == null) {
                throw new ResourceNotFound("Department not found" , "ERR1");
            }
            job.setDepartment(department);
            jobDAO.save(job, em);
        });
    }

    public static void updateJobOpening(JobDto jobDto) {
        Database.doInTransactionWithoutResult(em -> {
            JobDAO jobDAO = new JobDAO();
            Job job = jobDAO.get(jobDto.getJobId(), em).orElse(null);
            if (job == null) {
                throw new ResourceNotFound("Job not found" , "ERR1");
            }
            job.setJobName(jobDto.getJobName());
            job.setRequirements(jobDto.getRequirements());
            System.out.println("****>"+jobDto.getRequirements() );
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.get(jobDto.getDepartmentProviderId(), em).orElse(null);
            if (department == null) {
                throw new ResourceNotFound("Department not found" , "ERR1");
            }
            job.setDepartment(department);
            jobDAO.update(job, em);
        });

    }

    public static void deleteJobOpening(int id) {
        Database.doInTransactionWithoutResult(em -> {
            JobDAO jobDAO = new JobDAO();
            Job job = jobDAO.get(id, em).orElse(null);
            if (job == null) {
                throw new ResourceNotFound("Job not found" , "ERR1");
            }
            jobDAO.delete(job, em);
        });
    }

    public static List<JobDto> getJobOpeningForDepartment(int departmentId) {
        return Database.doInTransaction(em -> {
            JobDAO jobDAO = new JobDAO();
            DepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = departmentDAO.get(departmentId, em).orElse(null);
            if (department == null) {
                throw new ResourceNotFound("Department not found" , "ERR1");
            }
            List<Job> jobs = jobDAO.getJobOpeningForDepartment(departmentId, em);
            return JobMapper.INSTANCE.toJobDtoList(jobs);
        });
    }
}
