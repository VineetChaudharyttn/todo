package com.ttn.todo.exception;

import com.ttn.todo.message.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(TaskNotFoundException.class)
    public final ResponseEntity<ErrorMessage> taskNotFound(TaskNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex,"Task not found");
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex,"what else do you want to add");
        return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

}
