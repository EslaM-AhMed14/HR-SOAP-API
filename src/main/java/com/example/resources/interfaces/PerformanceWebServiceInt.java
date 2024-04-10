package com.example.resources.interfaces;

import com.example.persistence.dto.PerformancReviewDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

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
