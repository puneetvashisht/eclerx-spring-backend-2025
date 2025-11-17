package com.pv.rest_api_app.utils;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseError {
    String error;
    String message;
    int status;
    LocalDateTime timestamp;

}
