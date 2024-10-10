package com.tnasfer.gbict.global.error.advice;


import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import com.tnasfer.gbict.global.responseDto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e){
        ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
        log.info("# BusinessLogicException exceptionCode : {} message : {} ",e.getExceptionCode(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e){
        ErrorResponse errorResponse = ErrorResponse.of(e.getConstraintViolations());
        log.info("# ConstraintViolationException ConstraintViolations : {} message : {} ",e.getConstraintViolations(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.of(e.getBindingResult());
    }

}
