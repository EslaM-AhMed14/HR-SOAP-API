package com.example.resources.interfaces;

import com.example.persistence.dto.JobDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;


import java.util.List;

@WebService(name = "EmployeeWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/JobOpeningWebService")
public interface JobWebServiceInt {

    @WebMethod(operationName = "getIt")
    public String getIt();

    @WebMethod(operationName = "getAllJobOpenings")
    public List<JobDto> getAllJobOpenings();

    @WebMethod(operationName = "createJobOpening")
    public String createJobOpening(JobDto jobDto);

    @WebMethod(operationName = "updateJobOpening")
    public String updateJobOpening(JobDto jobDto);

    @WebMethod(operationName = "deleteJobOpening")
    public String deleteJobOpening(@WebParam(name="id") int id);

    @WebMethod(operationName = "getJobOpeningForDepartment")
    public List<JobDto> getJobOpeningForDepartment(@WebParam(name = "departmentId") int departmentId);

}
