package com.facebook.facebookhybernate.serviceImpl;

import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.repos.UserRepository;
import com.facebook.facebookhybernate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    User user = new User();
    @Autowired
    UserService userService;
    @BeforeEach
    void init(){
        user.setId(223344244L);
        user.setFirstname("Obrien");
        user.setLastname("Longe");
        user.setMobile("09087876545");
        user.setPassword("hfdvffvgfvghvfghsvhgvgfsdsdffsdfsf");
        user.setSex("Male");
        user.setAbout("vggvgvcsgvsgcvs");
        user.setEmail("hello@hello");
        user.setBirthdate("1990-02-28");
    }


    @Test
    void saveUserData() {
        userService.saveUserData(user);
    }

    @Test
    void userEmailExists() {
        assertTrue(userService.userEmailExists("hello@hello"));
    }

    @Test
    void userMobileExists() {
        assertTrue(userService.userMobileExists("09087876545"));
    }

    @Test
    void getUserById() {
     User returnedUser = userService.getUserById(1);
        assertEquals(user.getFirstname(), returnedUser.getFirstname());
    }

    @Test
    void getUserByEmail() {
        User returnedUser = userService.getUserByEmail("hello@hello");
        assertEquals(user.getFirstname(), returnedUser.getFirstname());
    }
}