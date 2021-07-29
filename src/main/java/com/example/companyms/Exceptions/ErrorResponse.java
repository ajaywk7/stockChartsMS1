package com.example.companyms.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private Date timestamp;
    private Long status;
    private String message;
    private Map<String , Object> details ;

    public ErrorResponse(String status, String message) {
        this.status = Long.parseLong( status.split(" ")[0]);
        this.message = message;
        this.timestamp = new Date();
    }
}
