package com.example.resources.interfaces;


import com.example.persistence.dto.SalaryDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

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
    public String bonusForAllEmployees(@WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "DeductionsForAllEmployees")
    public String deductionsForAllEmployees(@WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "BonusForEmployee")
    public String bonusForEmployee(@WebParam (name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage);

    @WebMethod(operationName = "DeductionForEmployee")
    public String deductionForEmployee(@WebParam(name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage);



}
