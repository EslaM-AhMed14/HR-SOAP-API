package com.example.resources.webServicesImpl;

import com.example.CustomException.ResourceNotFound;
import com.example.CustomException.UnlegalArgument;
import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.Service.LeaveManagementService;
import com.example.resources.webServicesInt.LeaveWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.ws.rs.QueryParam;

import java.util.List;


@WebService(endpointInterface = "com.example.resources.webServicesInt.LeaveWebServiceInt")
public class LeaveWebServiceImpl implements LeaveWebServiceInt {

    @Override
    @WebMethod(operationName = "getit")
    public String getit(){
        return "Leave Management";
    }

    @Override
    @WebMethod(operationName = "getAllLeaves")
    public List<LeaveManagementDto> getAllLeaves() {
        List<LeaveManagementDto> leaves = LeaveManagementService.getAllLeaves();
        if(leaves.isEmpty())
            throw new ResourceNotFound("No Leaves found" , "ERR1");
        return leaves;
    }

    @Override
    @WebMethod(operationName = "createLeave")
    public String createLeave(LeaveManagementDto performanceReviewDto) {
        try {
            System.out.println("resources Leave");
            LeaveManagementService.createLeave(performanceReviewDto);
            return "newLeave added successfully";

        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage() , e.getFaultCode());
        }
        catch (Exception e) {
            throw new ResourceNotFound("No Leaves found", "ERR5");
        }
    }

    @Override
    @WebMethod(operationName = "approveLeave")
    public String  approveLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId){
        try {
            LeaveManagementService.approveLeave(leaveId, managerId);
            return "Leave approved successfully";

        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage() , e.getFaultCode());
        }
        catch (Exception e) {
            throw new ResourceNotFound("No Leaves found", "ERR5");
        }

    }

    @Override
    @WebMethod(operationName = "rejectLeave")
    public String rejectLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId) {
        try {
            LeaveManagementService.rejectLeave(leaveId, managerId);
            return "Leave Rejected successfully";

        }catch (ResourceNotFound e) {
            throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage() , e.getFaultCode());
        }
        catch (Exception e) {
            throw new ResourceNotFound("No Leaves found", "ERR5");
        }

    }
}
