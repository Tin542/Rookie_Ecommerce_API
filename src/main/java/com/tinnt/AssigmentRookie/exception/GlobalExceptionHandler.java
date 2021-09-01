package com.tinnt.AssigmentRookie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    //Not found
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorDetail err = new ErrorDetail(LocalDateTime.now(),e.getMessage(), badRequest);
        return new ResponseEntity<>(err, badRequest);
    }

    //Add
    @ExceptionHandler(value = {AddException.class})
    public ResponseEntity<Object> handleAddException(AddException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorDetail err = new ErrorDetail(LocalDateTime.now(),e.getMessage(), badRequest);
        return new ResponseEntity<>(err, badRequest);
    }

    //Delete
    @ExceptionHandler(value = {DeleteException.class})
    public ResponseEntity<Object> handleDeleteException(DeleteException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorDetail err = new ErrorDetail(LocalDateTime.now(),e.getMessage(), badRequest);
        return new ResponseEntity<>(err, badRequest);
    }

    //update
    @ExceptionHandler(value = {UpdateException.class})
    public ResponseEntity<Object> handleUpdateException(UpdateException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorDetail err = new ErrorDetail(LocalDateTime.now(),e.getMessage(), badRequest);
        return new ResponseEntity<>(err, badRequest);
    }
}
