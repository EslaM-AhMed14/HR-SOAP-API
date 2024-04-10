package com.example.resources.implementation;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.DepartmentDto;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.service.DepartmentService;
import com.example.resources.interfaces.DepartmentWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Set;

@WebService(endpointInterface = "com.example.resources.interfaces.DepartmentWebServiceInt")
public class DepartmentWebServiceImpl implements DepartmentWebServiceInt {
    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Department Resource!";
    }

    @Override
    @WebMethod(operationName = "getAllDepartments")
    public List<DepartmentDto> getAllEmployees() {
        List<DepartmentDto> departments = DepartmentService.getAllDepartments();
        if (departments.isEmpty())
            throw new ResourceNotFound("No departments found" , "ERR1");
        return departments;
    }

    @Override
    @WebMethod(operationName = "getDepartmentById")
    public DepartmentDto getDepartmentById(@WebParam(name="DepatmentID") int id) {
        DepartmentDto departmentDto = DepartmentService.getDepartmentById(id);
        if (departmentDto == null)
            throw new ResourceNotFound("Department not found" , "ERR1");

        return departmentDto;
    }

    @Override
    @WebMethod(operationName = "getDepartmentByName")
    public DepartmentDto getDepartmentByName(@WebParam(name ="DepatmentName" ) String name) {
        DepartmentDto departmentDto = DepartmentService.getDepartmentByName(name);
        if (departmentDto == null)
            throw new ResourceNotFound("Department not found" , "ERR1");

        return departmentDto;
    }

    @Override
    @WebMethod(operationName = "addDepartment")
    public boolean addDepartment(@WebParam(name="DepartmentDetails") DepartmentDto departmentDto) {
        try {
            boolean addedDepartment = DepartmentService.addDepartment(departmentDto);
            if (!addedDepartment)
                throw new ResourceNotFound("Department not added" , "ERR1");

            return addedDepartment;
        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }
    }

    @Override
    @WebMethod(operationName = "updateDepartment")
    public boolean updateDepartment(@WebParam(name="DepartmentDetails") DepartmentDto departmentDto) {
        try {
            boolean updatedDepartment = DepartmentService.updateDepartment(departmentDto);
            if (!updatedDepartment)
                throw new ResourceNotFound("Department not updated" , "ERR1");

            return updatedDepartment;
        }catch (ResourceNotFound e){
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }
    }

    @Override
    @WebMethod(operationName = "getAllEmployeesByDepartmentId")
    public Set<EmployeeDto> getAllEmployeesByDepartment(@WebParam(name = "DepartmentId") int id) {
        try {
            Set<EmployeeDto> employeeDtos = DepartmentService.getAllEmployeesByDepartment(id);
            if (employeeDtos.isEmpty())
                throw new ResourceNotFound("No Employees found" , "ERR1");

            return employeeDtos;
        }catch (ResourceNotFound e){
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }
    }

    @Override
    @WebMethod(operationName = "deleteDepartment")
    public boolean deleteDepartment(@WebParam(name = "DepartmentId") int id) {
        try {
            return DepartmentService.deleteDepartment(id);

        }catch (ResourceNotFound e){
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }
    }

    @Override
    @WebMethod(operationName = "getAllManager")
    public  List<EmployeeDto> getAllManager() {
        try {
            List<EmployeeDto> employees = DepartmentService.getAllManager();
            if (employees.isEmpty())
                throw new ResourceNotFound("No Manager Found" , "ERR1");

            return employees;
        }catch (ResourceNotFound e){
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound(e.getMessage());
        }
    }
}
