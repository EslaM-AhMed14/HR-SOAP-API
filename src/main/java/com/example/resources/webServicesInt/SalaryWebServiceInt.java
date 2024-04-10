package com.example.resources.webServicesInt;


import com.example.persistence.DTOs.SalaryDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService(name = "SalaryWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/SalaryWebService")
public interface SalaryWebServiceInt {

    @WebMethod(operationName = "getIt")
    public String getIt();

    @WebMethod(operationName = "getBasicSalary")
    public SalaryDto getBasicSalary(@WebParam(name = "employeeId")  int id);

    @WebMethod(operationName = "getBasicSalaries")
    public List<SalaryDto> getBasicSalaries();

    @WebMethod(operationName = "getBasicSalaryRange")
    public  List<SalaryDto> getBasicSalaryRange(@WebParam(name = "min") int min, @WebParam (name="max") int max);


    @WebMethod(operationName = "BonusForAllEmployees")
    public String BonusForAllEmployees(@WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "DeductionsForAllEmployees")
    public String DeductionsForAllEmployees(@WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "BonusForEmployee")
    public String BonusForEmployee(@WebParam (name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "DeductionForEmployee")
    public String DeductionForEmployee(@WebParam(name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage);



}
