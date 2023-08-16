package com.example.demo.exceptions.handler;

import com.example.demo.exceptions.ExistObjectException;
import com.example.demo.exceptions.NotExistObjectException;
import com.example.demo.exceptions.NotExistsTicketNumberException;
import com.example.demo.exceptions.UnavaliableSeatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AirlineSystemExceptionHandler {
    @ExceptionHandler(value = ExistObjectException.class)
    public ResponseEntity<String> ExistObjectException(ExistObjectException exception) {
        return new ResponseEntity<>("The record is already exist!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotExistObjectException.class)
    public ResponseEntity<String> NotExistObjectException(NotExistObjectException exception) {
        return new ResponseEntity<>("There is no record!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnavaliableSeatException.class)
    public ResponseEntity<String> UnavaliableSeatException(UnavaliableSeatException exception) {
        return new ResponseEntity<>("Unavaliable Seat!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotExistsTicketNumberException.class)
    public ResponseEntity<String> NotExistsTicketNumberException(NotExistsTicketNumberException exception) {
        return new ResponseEntity<>("There is no such ticket number!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
