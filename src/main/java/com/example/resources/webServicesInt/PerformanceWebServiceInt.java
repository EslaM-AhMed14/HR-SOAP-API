package com.example.resources.webServicesInt;

import com.example.persistence.DTOs.PerformancReviewDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService(name = "PerformanceWebServiceInt", targetNamespace = "http://webServicesInt.resources.example.com/PerformanceWebService")
public interface PerformanceWebServiceInt {

    @WebMethod(operationName = "getIt")
    public String getIt();

    @WebMethod(operationName = "getAllPerformanceReview")
    public List<PerformancReviewDto> getAllPerformanceReview();

    @WebMethod(operationName = "addPerformanceReview")
    public String addPerformanceReview(PerformancReviewDto performanceReviewDto);


}
