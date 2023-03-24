package com.fs.middlelink.medication.exception.handler;

import com.fs.middlelink.medication.exception.MedicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> confilctHandler(MedicationException medicationException) {
        return new ResponseEntity<>(medicationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
