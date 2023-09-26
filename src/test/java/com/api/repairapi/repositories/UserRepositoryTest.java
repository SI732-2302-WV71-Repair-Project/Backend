package com.api.repairapi.repositories;

import com.api.repairapi.models.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void testFindByEmailAndPassword(){

        UserModel user = new UserModel();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setCellphoneNumber("123456789");
        user.setBirthDate(new Date());
        user.setGender('T');
        user.setLastConnection(new Date());
        user.setAddress("Test");
        user.setDistrict("Test");

        user.setEmail("text@example.com");
        user.setPassword("12345678");

        userRepository.save(user);

        Optional<UserModel> userFound = userRepository.findByEmailAndPassword("text@example.com", "12345678");

        assertTrue(userFound.isPresent());
        assertEquals(user.getEmail(), userFound.get().getEmail());
        assertEquals(user.getPassword(), userFound.get().getPassword());

    }


}
