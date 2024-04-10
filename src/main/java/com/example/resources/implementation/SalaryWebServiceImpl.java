package com.example.resources.implementation;

import com.example.exception.ResourceNotFound;
import com.example.exception.UnlegalArgument;
import com.example.persistence.dto.SalaryDto;
import com.example.persistence.service.SalaryService;
import com.example.resources.interfaces.SalaryWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.example.resources.interfaces.SalaryWebServiceInt")
public class SalaryWebServiceImpl  implements SalaryWebServiceInt {
    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Salary Resource!";
    }

    @Override
    public SalaryDto getBasicSalary(int id) {
        try {
            return SalaryService.getBasicSalary(id);
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
            return  SalaryService.getBasicSalaries();
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
            return  SalaryService.getBasicSalaryRange(min, max);

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
    public String bonusForAllEmployees(@WebParam(name = "percentage") double percentage) {
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
    public String deductionsForAllEmployees(@WebParam(name = "percentage") double percentage) {
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
    public String bonusForEmployee(@WebParam (name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage) {
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
    public String deductionForEmployee(@WebParam(name = "employeeId") int employeeId, @WebParam(name = "percentage") double percentage) {
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
