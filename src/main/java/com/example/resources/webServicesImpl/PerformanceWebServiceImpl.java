package com.example.resources.webServicesImpl;

import com.example.CustomException.ResourceNotFound;
import com.example.CustomException.UnlegalArgument;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.Service.PerformanceServace;
import com.example.resources.webServicesInt.PerformanceWebServiceInt;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService(endpointInterface = "com.example.resources.webServicesInt.PerformanceWebServiceInt")
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
            List<PerformancReviewDto> performances = PerformanceServace.getAllPerformanceReview();
            return performances;
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
