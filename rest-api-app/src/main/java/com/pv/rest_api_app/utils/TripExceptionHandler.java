package com.pv.rest_api_app.utils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TripExceptionHandler {

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseError handleTripNotFoundException(TripNotFoundException ex) {
        ResponseError error = new ResponseError();
        error.setError("Not Found");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleConflicts(DataIntegrityViolationException ex) {
        // log.error("Data integ", ex);
        log.error("Data integrity violation: {}", ex.getMessage());
        ResponseError error = new ResponseError();
        error.setError("Internal Server Error");
        error.setMessage("Duplicate record or data integrity violation");
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(Exception.class)
    public ResponseError handleAllExceptions(Exception ex) {
        ResponseError error = new ResponseError();
        error.setError("Internal Server Error");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

}
