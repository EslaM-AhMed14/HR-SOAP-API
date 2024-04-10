package com.example.resources.implementation;

import com.example.exception.ResourceNotFound;
import com.example.exception.UnlegalArgument;
import com.example.persistence.dto.PerformancReviewDto;
import com.example.persistence.service.PerformanceServace;
import com.example.resources.interfaces.PerformanceWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.example.resources.interfaces.PerformanceWebServiceInt")
public class PerformanceWebServiceImpl implements PerformanceWebServiceInt {

    @Override
    @WebMethod(operationName = "getIt")
    public String getIt() {
        return "Performance Review";
    }

    @Override
    @WebMethod(operationName = "getAllPerformanceReview")
    public List<PerformancReviewDto> getAllPerformanceReview() {
        try {
            return PerformanceServace.getAllPerformanceReview();

        }catch (ResourceNotFound e) {
           throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("No Performance Review found" , "ERR1");
        }
    }

    @Override
    @WebMethod(operationName = "addPerformanceReview")
    public String addPerformanceReview(PerformancReviewDto performanceReviewDto) {
        try {
            PerformanceServace.addPerformanceReview(performanceReviewDto);
            return "newPerformance added successfully";
        }catch (ResourceNotFound e) {
          throw new ResourceNotFound(e.getMessage() , e.getFaultCode());
        }catch (UnlegalArgument e) {
            throw new UnlegalArgument(e.getMessage(), e.getFaultCode());
        }catch (Exception e) {
            throw new ResourceNotFound("No Performance Review found" , "ERR1");
        }
    }
}
