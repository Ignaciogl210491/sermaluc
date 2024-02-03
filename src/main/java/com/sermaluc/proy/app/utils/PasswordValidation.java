package com.sermaluc.proy.app.utils;

import com.sermaluc.proy.app.exceptions.UserException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class PasswordValidation {


    private String customRegex;

    public boolean isValidPassword(String password) throws UserException {
        Pattern pattern = Pattern.compile("((?=.*\\d).{8,20})");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()) {
            throw new UserException( HttpStatus.CONFLICT,"Password invalido");
        }
        return true;
    }
}
