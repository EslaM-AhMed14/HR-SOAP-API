package com.example.resources.interfaces;


import com.example.persistence.dto.DepartmentDto;
import com.example.persistence.dto.EmployeeDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Set;

@WebService(name = "DepartmentWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/DepartmentWebService")
public interface DepartmentWebServiceInt {

    @WebMethod(operationName = "getIt")
    public String getIt();

    @WebMethod(operationName = "getAllDepartments")
    public List<DepartmentDto> getAllEmployees();

    @WebMethod(operationName = "getDepartmentById")
    public DepartmentDto getDepartmentById(@WebParam(name="DepatmentID") int id);

    @WebMethod(operationName = "getDepartmentByName")
    public DepartmentDto getDepartmentByName(@WebParam(name ="DepatmentName" ) String name);

    @WebMethod(operationName = "addDepartment")
    public boolean addDepartment(@WebParam(name="DepartmentDetails") DepartmentDto departmentDto);

    @WebMethod(operationName = "updateDepartment")
    public boolean updateDepartment(@WebParam(name="DepartmentDetails") DepartmentDto departmentDto);


    @WebMethod(operationName = "getAllEmployeesByDepartmentId")
    public Set<EmployeeDto> getAllEmployeesByDepartment(@WebParam(name = "DepartmentId") int id);

    @WebMethod(operationName = "deleteDepartment")
    public boolean deleteDepartment(@WebParam(name = "DepartmentId") int id);


    @WebMethod(operationName = "getAllManager")
    public  List<EmployeeDto> getAllManager();




}
