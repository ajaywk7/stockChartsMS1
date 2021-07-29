package com.example.companyms.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class SuccessResponse {
    private Date timestamp;
    private Long status;
    private String message;

    public SuccessResponse(String message) {
        this.status = 200l;
        this.message = message;
        this.timestamp = new Date();
    }
}
