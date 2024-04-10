package com.example.exception;

import lombok.*;


@Getter
@Setter
public class ErrorMessage {
    private  String message;
    private  int errorCode;
    private  String details;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, int i, String s) {
        this.message = message;
        this.errorCode = i;
        this.details = s;
    }

    public ErrorMessage(String message, int i) {
        this.message = message;
        this.errorCode = i;
    }
}
