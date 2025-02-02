package com.example.resources.interfaces;

import com.example.persistence.dto.EmployeeDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(name = "EmployeeWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/EmployeeWebService")
public interface EmployeeWebServiceInt {

    @WebMethod(operationName = "getIt")
    public String getIt();

    @WebMethod(operationName = "getAllEmployees")
    public List<EmployeeDto> getAllEmployees();

    @WebMethod(operationName = "getEmployeeById")
    public EmployeeDto getEmployeeById(@WebParam(name = "employeeId") int id);

    @WebMethod(operationName = "addEmployee")
    public boolean addEmployee(EmployeeDto employeeDto);

    @WebMethod(operationName = "deleteEmployee")
    public boolean deleteEmployee(@WebParam(name = "employeeId") int id);

    @WebMethod(operationName = "updateEmployee")
    public boolean updateEmployee(EmployeeDto employeeDto);


}
