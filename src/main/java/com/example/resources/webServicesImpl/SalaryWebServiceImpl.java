package com.example.resources.webServicesImpl;

import com.example.CustomException.ErrorMessage;
import com.example.CustomException.ResourceNotFound;
import com.example.CustomException.UnlegalArgument;
import com.example.persistence.DTOs.SalaryDto;
import com.example.persistence.Service.SalaryService;
import com.example.resources.webServicesInt.SalaryWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService(endpointInterface = "com.example.resources.webServicesInt.SalaryWebServiceInt")
public class SalaryWebServiceImpl  implements SalaryWebServiceInt {
    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Salary Resource!";
    }

    @Override
    public SalaryDto getBasicSalary(int id) {
        try {
            SalaryDto salaryDto = SalaryService.getBasicSalary(id);
            return salaryDto;
        } catch (ResourceNotFound e) {
                throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }

    @Override
    @WebMethod(operationName = "getBasicSalaries")
    public List<SalaryDto> getBasicSalaries() {
        try {
            List<SalaryDto>  salaryDtos= SalaryService.getBasicSalaries();
            return salaryDtos;
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }

    @Override
    @WebMethod(operationName = "getBasicSalaryRange")
    public  List<SalaryDto> getBasicSalaryRange(@WebParam(name = "min") int min, @WebParam (name="max") int max) {
        try {
            List<SalaryDto> salaryDtos = SalaryService.getBasicSalaryRange(min, max);
            return salaryDtos;
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }

    @Override
    @WebMethod(operationName = "BonusForAllEmployees")
    public String BonusForAllEmployees(@WebParam(name = "percentage") double percentage) {
        try {
            SalaryService.applayBonusForAllEmployees(percentage);
            return "Bonus for all employees increased by " + percentage + "%";
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }

    @Override
    @WebMethod(operationName = "DeductionsForAllEmployees")
    public String DeductionsForAllEmployees(@WebParam(name = "percentage") double percentage) {
        try {
            SalaryService.applayDeductionsForAllEmployees(percentage);
            return "Deduction for all employees increased by " + percentage + "%";
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }

    @Override
    @WebMethod(operationName = "BonusForEmployee")
    public String BonusForEmployee(@WebParam (name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage) {
        try {
            SalaryService.applayBonusForEmployee(employeeId, percentage);
            return "Bonus for employee with id: " + employeeId + " increased by " + percentage + "%";
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }

    }

    @Override
    @WebMethod(operationName = "DeductionForEmployee")
    public String DeductionForEmployee(@WebParam(name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage) {
        try {
            SalaryService.applayDeductionsForEmployee(employeeId, percentage);
            return "Deduction for employee with id: " + employeeId + " increased by " + percentage + "%";
        } catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() ,e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("Not found the Salary in database", "ERR3");
        }
    }
}
