package com.obrien.picesmart.services.impl;

import com.obrien.picesmart.model.User;
import com.obrien.picesmart.repository.UserRepository;
import com.obrien.picesmart.services.UserService;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;
import com.obrien.picesmart.utils.ServiceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    UserReceivingDTO userReceivingDTO = new UserReceivingDTO();
    SignInReceivingDTO signInReceivingDTO = null;

    @BeforeEach
    void init(){
        userReceivingDTO.setEmail("obrien@hello.com");
        userReceivingDTO.setFirstName("Obrien");
        userReceivingDTO.setLastName("Longe");
        userReceivingDTO.setMobile("0700000000");
        userReceivingDTO.setPassword("hello");

    }

    @Test
    void registerUser() {
     ServiceResponse response = userService.registerUser(userReceivingDTO);
     List<User> users = userRepository.findByEmail(userReceivingDTO.getEmail());
     assertEquals(1, users.size(), "Method should register user");
     assertTrue(response.isStatus());
    }

    @Test
    void userLoginWithEmail() {
        userService.registerUser(userReceivingDTO);
        signInReceivingDTO = new SignInReceivingDTO("obrien@hello.com", "hello");
        ServiceResponse response = userService.userLogin(signInReceivingDTO);
        UserSendingDTO user = (UserSendingDTO) response.getData();
        assertTrue(response.isStatus(), "method should login user");
        assertEquals(userReceivingDTO.getEmail(), user.getEmail(), "Method should return correct user");
    }

    @Test
    void userLoginWithMobile() {
        userService.registerUser(userReceivingDTO);
        signInReceivingDTO = new SignInReceivingDTO("0700000000", "hello");
        ServiceResponse response = userService.userLogin(signInReceivingDTO);
        UserSendingDTO user = (UserSendingDTO) response.getData();
        assertTrue(response.isStatus(), "method should login user");
        assertEquals(userReceivingDTO.getEmail(), user.getEmail(), "Method should return correct user");
    }

    @Test
    void userInvalidLoginDetail() {
        userService.registerUser(userReceivingDTO);
        signInReceivingDTO = new SignInReceivingDTO("obrien@hello.co", "hello");
        ServiceResponse response = userService.userLogin(signInReceivingDTO);

        assertFalse(response.isStatus(), "method should not login user");
        assertEquals("Invalid Mobile Number/Email", response.getMessage(), "Method should return correct message");
    }

    @Test
    void userInvalidLoginPassword() {
        userService.registerUser(userReceivingDTO);
        signInReceivingDTO = new SignInReceivingDTO("obrien@hello.com", "hlo");
        ServiceResponse response = userService.userLogin(signInReceivingDTO);
        assertFalse(response.isStatus(), "method should not login user");
        assertEquals("Invalid Password", response.getMessage(), "Method should return correct message");
    }

    @Test
    void getUserById() {
        userService.registerUser(userReceivingDTO);
        signInReceivingDTO = new SignInReceivingDTO("obrien@hello.com", "hello");
        ServiceResponse response = userService.userLogin(signInReceivingDTO);
        UserSendingDTO user = (UserSendingDTO) response.getData();
        User  returnedUser = userService.getUserById(user.getId());
        assertEquals(user.getEmail(), returnedUser.getEmail(), "Method should return correct user");
        assertEquals(user.getFirstName(), returnedUser.getFirstName(), "Method should return correct user");
        assertEquals(user.getLastName(), returnedUser.getLastName(), "Method should return correct user");
    }
}