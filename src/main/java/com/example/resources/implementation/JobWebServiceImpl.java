package com.example.resources.implementation;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.JobDto;
import com.example.persistence.service.JobService;
import com.example.resources.interfaces.JobWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;


import java.util.List;

@WebService(endpointInterface = "com.example.resources.interfaces.JobWebServiceInt")
public class JobWebServiceImpl  implements JobWebServiceInt {
    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Job Opening Resource!";
    }

    @Override
    @WebMethod(operationName = "getAllJobOpenings")
    public List<JobDto> getAllJobOpenings() {
        try {
            return JobService.getAllJobOpenings();

        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage() , "ERR5");
        }
    }

    @Override
    @WebMethod(operationName = "createJobOpening")
    public String createJobOpening(JobDto jobDto) {
        try {
            JobService.createJobOpening(jobDto);
            return "new Job Opening added successfully";

        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage(), e.getFaultCode());
        } catch (Exception e) {
            throw new ResourceNotFound(e.getMessage(), "ERR5");
        }
    }


    @Override
    public String updateJobOpening(JobDto jobDto) {
        try {
            JobService.updateJobOpening(jobDto);
            return "Job Opening updated successfully";

        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage(), "ERR5");
        }
    }

    @Override
    @WebMethod(operationName = "updateJobOpening")
    public String deleteJobOpening(int id) {
        try {
            JobService.deleteJobOpening(id);
            return "Job Opening deleted successfully";

        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage(),e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage(), "ERR5");
        }
    }

    @Override
    @WebMethod(operationName = "getJobOpeningForDepartment")
    public List<JobDto> getJobOpeningForDepartment(@WebParam(name = "departmentId") int departmentId) {
        try {
            return  JobService.getJobOpeningForDepartment(departmentId);

        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage() , "ERR5");
        }
    }
}
