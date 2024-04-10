package com.example.resources.implementation;

import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.service.EmployeeService;
import com.example.resources.interfaces.EmployeeWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.example.resources.interfaces.EmployeeWebServiceInt")
public class EmployeeWebServiceImpl implements EmployeeWebServiceInt {
    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Hello, To Employee Resource!";
    }

    @Override
    @WebMethod(operationName = "getAllEmployees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employees = EmployeeService.getAllEmployees();
        if (employees.isEmpty())
            throw new ResourceNotFound("No Employees found");

        return employees;
    }

    @Override
    @WebMethod(operationName = "getEmployeeById")
    public EmployeeDto getEmployeeById(@WebParam(name = "employeeId") int id) {
        EmployeeDto employeeDto = EmployeeService.getEmployeeById(id);
        if (employeeDto == null){
           throw new ResourceNotFound("Employee not found" ,"ERR1");
        }

        return employeeDto;
    }

    @Override
    @WebMethod(operationName = "addEmployee")
    public boolean addEmployee(EmployeeDto employeeDto) {
        boolean addedEmployee = EmployeeService.addEmployee(employeeDto);
        if (!addedEmployee){
            throw new ResourceNotFound("Employee not added","ERR2");
        }

        return addedEmployee;
    }

    @Override
    @WebMethod(operationName = "deleteEmployee")
    public boolean deleteEmployee(@WebParam(name = "employeeId") int id) {
        boolean done =  EmployeeService.deleteEmployee(id);
        if (!done)
            throw new ResourceNotFound("Employee not found" ,"ERR1");

        return done;
    }

    @Override
    @WebMethod(operationName = "updateEmployee")
    public boolean updateEmployee(EmployeeDto employeeDto) {
        try{
            boolean updatedEmployee = EmployeeService.updateEmployee(employeeDto);
            if (!updatedEmployee)
                throw new ResourceNotFound("Employee not updated" ,"ERR1");

            return updatedEmployee;
        }catch (ResourceNotFound e){
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (Exception e){
            throw new ResourceNotFound(e.getMessage());
        }
    }
}
