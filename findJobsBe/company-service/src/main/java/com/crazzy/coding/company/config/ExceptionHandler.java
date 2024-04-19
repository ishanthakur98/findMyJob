package com.crazzy.coding.company.config;

import com.crazzy.coding.company.exception.CompanyException;
import com.crazzy.coding.company.modal.CompanyExceptionSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CompanyException.class)
    public ResponseEntity<Object> exception(CompanyException exception){
        CompanyExceptionSchema exceptionSchema = new CompanyExceptionSchema();
        exceptionSchema.setErrorCode(String.valueOf(exception.getErrorCode()));
        exceptionSchema.setErrorDescription(exception.getErrorDescription());
        exceptionSchema.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionSchema, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        CompanyExceptionSchema ex = new CompanyExceptionSchema();
        ex.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        ex.setErrorDescription(exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : "Something went wrong . Please try again later");
        ex.setLocalDateTime(LocalDateTime.now());
        log.error("{} ",exception);
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElement(Exception exception) {
        CompanyExceptionSchema ex = new CompanyExceptionSchema();
        ex.setErrorCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
        ex.setErrorDescription("No content found for this request");
        ex.setLocalDateTime(LocalDateTime.now());
        log.error("{} ",exception);
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}
