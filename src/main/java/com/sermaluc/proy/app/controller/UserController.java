package com.sermaluc.proy.app.controller;

import com.sermaluc.proy.app.exceptions.UserException;
import com.sermaluc.proy.app.model.User;
import com.sermaluc.proy.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/resgistro")
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) throws UserException {
        if(result.hasErrors()){
            Map<String, Object> response = new HashMap<>();
            List<String> errors= result.getFieldErrors().stream().map(err -> "El campo'" + err.getField() +"' "+ err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userService.save(user));

        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result) throws UserException {
        if(result.hasErrors()){
            Map<String, Object> response = new HashMap<>();
            List<String> errors= result.getFieldErrors().stream().map(err -> "El campo'" + err.getField() +"' "+ err.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }else{
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(userService.update(user));

        }
    }

}
