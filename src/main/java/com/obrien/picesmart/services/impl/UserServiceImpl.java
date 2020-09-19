package com.obrien.picesmart.services.impl;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.model.User;
import com.obrien.picesmart.repository.UserRepository;
import com.obrien.picesmart.services.UserService;
import com.obrien.picesmart.utils.DTO.BrandSendingDTO;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;
import com.obrien.picesmart.utils.ServiceResponse;
import com.obrien.picesmart.utils.builders.BrandBuilder;
import com.obrien.picesmart.utils.builders.BrandSenderBuilder;
import com.obrien.picesmart.utils.builders.UserBuilder;
import com.obrien.picesmart.utils.builders.UserSenderBuilder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method creates and registers a new user
     * It checks if user email and mobile already exists in the database
     * It hashed the passwords then calls the user builder which return a new
     * user model which is then saved to the database
     * @param userReceivingDTO
     * @return
     */
    @Override
    public ServiceResponse registerUser(UserReceivingDTO userReceivingDTO) {
        if(emailExists(userReceivingDTO.getEmail())){
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Email Already Exists").setStatus(false).build();
        }
        if(mobileExists(userReceivingDTO.getMobile())) {
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Mobile Already Exists").setStatus(false).build();
        }

        String PASSWORD_HASH = BCrypt.hashpw(userReceivingDTO.getPassword(), BCrypt.gensalt());
        User newUser = new UserBuilder().setUser(userReceivingDTO).setHash(PASSWORD_HASH).build();
        userRepository.save(newUser);
        return new ServiceResponse.ServiceResponseBuilder().setCode(200)
                .setMessage("Success").setStatus(true).build();
    }


    /**
     * Method Handles user login. It first searches for user by email
     * and if no user is returned it searches for user by mobile number
     * if no user with the inputted details is seen it returns returns an error message
     * else it checks if the returned users password matches the hash code in database
     * it sends the user and a success message if it matches else it returns an error message
     * @param signInReceivingDTO
     * @return ServiceResponse
     */
    @Override
    public ServiceResponse userLogin(SignInReceivingDTO signInReceivingDTO) {

        User user = getUserByEmail(signInReceivingDTO.getDetails());
        if(user == null){
            user = getUserByMobile(signInReceivingDTO.getDetails());
        }
        if(user == null){
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Invalid Mobile Number/Email").setStatus(false).build();
        }
        if(null == signInReceivingDTO.getPassword() || !user.getHash().startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        if(!BCrypt.checkpw(signInReceivingDTO.getPassword(), user.getHash() )) {
            return new ServiceResponse(false, "Invalid Password", 202);
        }

        UserSendingDTO userSendingDTO = new UserSenderBuilder().setUser(user).build();
        return new ServiceResponse(true, "Success", 200, userSendingDTO);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }

    @Override
    public UserSendingDTO getUserSendingDTOById(long id) {
        User user =  getUserById( id);
        if(user == null){
            return null;
        }
        UserSendingDTO userSendingDTO = new UserSenderBuilder().setUser(user).build();
        return  userSendingDTO;
    }

    @Override
    public User editUser(UserReceivingDTO userReceivingDTO, long userId){
        User user = getUserById(userId);
        if(user == null) return null;
        String firstName = userReceivingDTO.getFirstName();
        String lastName = userReceivingDTO.getLastName();
        String email = userReceivingDTO.getEmail();
        String mobile = userReceivingDTO.getMobile();
        if(firstName != null) user.setFirstName(firstName);
        if(lastName != null) user.setLastName(lastName);
        if(mobile != null) user.setMobile(mobile);
        if(email != null) user.setEmail(email);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }

    private boolean emailExists(String email) {
        List<User> users = userRepository.findByEmail(email);
        if(users.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean mobileExists(String mobile) {
        List<User> users = userRepository.findByMobile(mobile);
        if(users.isEmpty()){
            return false;
        }
        return true;
    }

    private User getUserByEmail(String email){
        List<User> users = userRepository.findByEmail(email);
        System.out.println(email);
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    private User getUserByMobile(String mobile){
        List<User> users = userRepository.findByMobile(mobile);
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }


}
