package com.example.exception;

import jakarta.xml.ws.WebFault;

@WebFault
public class ResourceNotFound extends RuntimeException{
    private String faultCode;

    public ResourceNotFound(String message, String faultCode, Throwable cause) {
        super(message, cause);
        this.faultCode = faultCode;
    }

    public ResourceNotFound(String message, String faultCode) {
        super(message);
        this.faultCode = faultCode;
    }
    public ResourceNotFound(String message) {
        super(message);
    }

    public String getFaultCode() {
        return faultCode;
    }
}
