package com.example.exception;

import jakarta.xml.ws.WebFault;

@WebFault
public class UnlegalArgument extends IllegalArgumentException{
    private String faultCode;

    public UnlegalArgument(String message, String faultCode, Throwable cause) {
        super(message, cause);
        this.faultCode = faultCode;
    }

    public UnlegalArgument(String message, String faultCode) {
        super(message);
        this.faultCode = faultCode;
    }

    public String getFaultCode() {
        return faultCode;
    }
}
