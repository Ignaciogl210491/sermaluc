package com.sermaluc.proy.app.repository;
import static org.assertj.core.api.Assertions.assertThat;
import com.sermaluc.proy.app.model.Phone;
import com.sermaluc.proy.app.model.User;
import com.sermaluc.proy.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup(){
        Phone phone=Phone.builder().phone_id(1l).cityCode(1243).number(3223455).countryCode(51).build();
        List<Phone> phones= new ArrayList<>();
        phones.add(phone);
        user= User.builder().id(1l).name("cris").email("juan2@rodriguez.org").password("hun34322344355").active(true).created(LocalDateTime.now()).lastLogin(LocalDateTime.now()).phones(phones).build();

    }
    @DisplayName("Guardar usuario")
    @Test
    public void testGuardarUser(){

        User userGuardado= userRepository.save(user);

        assertThat(userGuardado).isNotNull();
        assertThat(userGuardado.getId()).isGreaterThan(0);

    }

    @DisplayName("Actualizar usuario")
    @Test
    public void testUpdateUser(){

        user.setModified(LocalDateTime.now());


        User userModificado= userRepository.save(user);

        assertThat(userModificado).isNotNull();
        assertThat(userModificado.getId()).isGreaterThan(0);
        assertThat(userModificado.getModified()).isNotNull();

    }
}
