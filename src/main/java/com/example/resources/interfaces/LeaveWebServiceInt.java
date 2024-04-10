package com.example.resources.interfaces;


import com.example.persistence.dto.LeaveManagementDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.ws.rs.QueryParam;

import java.util.List;

@WebService(name = "LeaveWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/LeaveWebService")
public interface LeaveWebServiceInt {

    @WebMethod(operationName = "getit")
    public String getit();

    @WebMethod(operationName = "getAllLeaves")
    public List<LeaveManagementDto> getAllLeaves();

    @WebMethod(operationName = "createLeave")
    public String createLeave(LeaveManagementDto performanceReviewDto);

    @WebMethod(operationName = "approveLeave")
    public String  approveLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId);


    @WebMethod(operationName = "rejectLeave")
    public String rejectLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId);

}
