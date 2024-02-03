package com.sermaluc.proy.app.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.sermaluc.proy.app.exceptions.UserException;
import com.sermaluc.proy.app.model.Phone;
import com.sermaluc.proy.app.model.User;
import com.sermaluc.proy.app.repositories.UserRepository;
import com.sermaluc.proy.app.security.JwtService;
import com.sermaluc.proy.app.services.UserService;
import com.sermaluc.proy.app.services.UserServiceImp;
import com.sermaluc.proy.app.utils.PasswordValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordValidation passwordValidation;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userServiceImp= new UserServiceImp(userRepository,passwordValidation,jwtService);


    @DisplayName("Crear usuario")
    @Test
    public void testCrearUser() throws UserException {
        Phone phone=Phone.builder().phone_id(1l).cityCode(1243).number(3223455).countryCode(51).build();
        List<Phone> phones= new ArrayList<>();
        phones.add(phone);
        User user= User.builder().id(1l).name("cris").email("juan2@rodriguez.org").password("hun34322344355").active(true).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).phones(phones).build();

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.empty());

        given(passwordValidation.isValidPassword(user.getPassword())).willReturn(true);

        given(userRepository.save(user)).willReturn(user);


         User userGuardado= userServiceImp.save(user);

         assertThat(userGuardado).isNotNull();
        assertThat(userGuardado.getId()).isGreaterThan(0);

    }

    @DisplayName("Actualizar usuario")
    @Test
    public void testUpdateUser() throws UserException {
        Phone phone=Phone.builder().phone_id(1l).cityCode(1243).number(3223455).countryCode(51).build();
        List<Phone> phones= new ArrayList<>();
        phones.add(phone);
        User user= User.builder().id(1l).name("cris").email("juan2@rodriguez.org").password("hun34322344355").active(false).modified(LocalDateTime.now()).phones(phones).build();

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.empty());

        given(passwordValidation.isValidPassword(user.getPassword())).willReturn(true);

        given(userRepository.save(user)).willReturn(user);


        User userGuardado= userServiceImp.save(user);

        assertThat(userGuardado).isNotNull();
        assertThat(userGuardado.getModified()).isNotNull();


    }


}
