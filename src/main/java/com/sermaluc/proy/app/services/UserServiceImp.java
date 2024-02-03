package com.sermaluc.proy.app.services;

import com.sermaluc.proy.app.exceptions.UserException;
import com.sermaluc.proy.app.model.User;
import com.sermaluc.proy.app.repositories.UserRepository;
import com.sermaluc.proy.app.security.JwtService;
import com.sermaluc.proy.app.utils.PasswordValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements  UserService{

    private UserRepository userRepository;
    private PasswordValidation passwordValidation;

    private JwtService jwtService;

    @Override
    public User save(User user) throws UserException {
        Optional<User> userValid= userRepository.findByEmail(user.getEmail());
         if(userValid.isPresent()){
             throw new UserException(HttpStatus.CONFLICT,"El correo ya registrado");
         }
         passwordValidation.isValidPassword(user.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setToken(jwtService.generatedToken(user));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws UserException {
        Optional<User> optionalUser= userRepository.findById(user.getId());
        if(optionalUser.isPresent()){
            User usuarioBD= optionalUser.get();
            usuarioBD.setEmail(user.getEmail());
            usuarioBD.setName(user.getName());
            usuarioBD.setModified(LocalDateTime.now());
            usuarioBD.setActive(user.isActive());
            usuarioBD.setPhones(user.getPhones());
            return userRepository.save(usuarioBD);
        }else{
            throw new UserException(HttpStatus.CONFLICT,"El Usuario con el ID: ".concat(user.getId().toString()).concat(" no existe"));
        }
    }

}
