package com.api.repairapi.services;

import com.api.repairapi.models.UserModel;
import com.api.repairapi.repositories.IUserRepository;
import org.apache.catalina.User;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.io.PrintStream;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @Spy
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    private final PrintStream originalOut = System.out;

    @Test
    public void testGetUserById(){
        Optional<UserModel> userFound = userRepository.findById(37L);

        Optional<UserModel> user = this.userService.getUserById(37L);

        assertEquals(userFound, user);

    }

    @Test
    public void testGetUsers(){

        //Arrange
        List<UserModel> expected = new ArrayList<>();
        expected.add(new UserModel());
        expected.add(new UserModel());

        // Mock
        Mockito.when(userRepository.findAll()).thenReturn(expected);

        // Act
        List<UserModel> actual = this.userService.getUsers();

        // Assert
        Mockito.verify(userRepository, times(1)).findAll();
        assertEquals(expected.size(), actual.size());
        assertEquals(expected, actual);
    }

    @Test
    public void testSaveUser(){

        //Arrange
        UserModel expected = new UserModel();
        expected.setFirstName("Willy");
        expected.setLastName("Valentin");
        expected.setEmail("wvaleric@gmail.com");
        expected.setAddress("Av. Salaverry 1014");
        expected.setCellphoneNumber("987654123");
        expected.setPassword("password");
        expected.setGender('M');
        expected.setDistrict("Jesus Maria");
        expected.setBirthDate(new Date());

        //Mock
        Mockito.when(userRepository.save(expected)).thenReturn(expected);

        //Act
        UserModel actual = this.userService.saveUser(expected);

        //Assert
        verify(userRepository,times(1)).save(expected);
        assertEquals(expected, actual);


    }

    @Test
    public void testUpdateById(){

        //Arrange
        UserModel expected = new UserModel();
        expected.setId(1L);
        expected.setFirstName("Willy");
        expected.setLastName("Valentin");
        expected.setEmail("wvaleric@gmail.com");
        expected.setAddress("Av. Salaverry 1014");
        expected.setCellphoneNumber("987654123");
        expected.setPassword("password");
        expected.setGender('M');
        expected.setDistrict("Jesus Maria");
        expected.setBirthDate(new Date());

        //Mock
        when(userRepository.findById(1L)).thenReturn(Optional.of(expected));
        when(userRepository.save(expected)).thenReturn(expected);

        //Act
        UserModel actual = userService.updateById(expected, 1L);

        //Assert
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteUserById(){
        Long userIdToDelete = 1L;

        //Act
        userService.deleteUserById(userIdToDelete);

        //Assert
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }

    @Test
    public void testDeleteUserByIdWithException(){
        Long userIdToDelete = 1L;

        //Mock
        doThrow(new RuntimeException("Error al eliminar el usuario")).when(userRepository).deleteById(userIdToDelete);

        //Act
        userService.deleteUserById(userIdToDelete);

        //Assert
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }

    @Test
    public void testGetUserByEmailAndPasswords(){
        //Arrange
        UserModel expected = new UserModel();
        expected.setFirstName("Test");
        expected.setLastName("Test");
        expected.setCellphoneNumber("123456789");
        expected.setBirthDate(new Date());
        expected.setGender('T');
        expected.setLastConnection(new Date());
        expected.setAddress("Test");
        expected.setDistrict("Test");
        expected.setEmail("text@example.com");
        expected.setPassword("12345678");

        //Mock
        Mockito.when(userRepository.findByEmailAndPassword("text@example.com", "12345678")).thenReturn(Optional.of(expected));

        //Act
        Optional<UserModel> actual = userService.getUserByEmailandPasswords("text@example.com", "12345678");

        //Asserts
        assertEquals(expected.getEmail(), actual.get().getEmail());
        assertEquals(expected.getPassword(), actual.get().getPassword());
    }

    @Test
    public void testGetUserByEmailAndPasswordsWithException(){

        //Mock
        Mockito.when(userRepository.findByEmailAndPassword("text@example.com", "12345678")).thenThrow(new RuntimeException("No se encontró el usuario"));

        //Act
        Optional<UserModel> actual = userService.getUserByEmailandPasswords("text@example.com", "12345678");

        //Asserts
        verify(userRepository, times(1)).findByEmailAndPassword("text@example.com", "12345678");
        assertEquals("No se encontró el usuario", outContent.toString().trim());

        System.setOut(originalOut);
    }
}
