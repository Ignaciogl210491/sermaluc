package com.sermaluc.proy.app.advisor;

import com.sermaluc.proy.app.exceptions.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ClobalException {
    @ExceptionHandler(UserException.class)
    public Map<String, String> handlerUserException(UserException exception){
        Map<String, String> erroMap= new HashMap<>();
        erroMap.put("message", exception.getMessage());
        return erroMap;
    }
}
