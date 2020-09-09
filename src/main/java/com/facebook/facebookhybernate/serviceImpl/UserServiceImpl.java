package com.facebook.facebookhybernate.serviceImpl;



import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.models.UserSignIn;
import com.facebook.facebookhybernate.repos.UserRepository;
import com.facebook.facebookhybernate.service.UserService;
import com.facebook.facebookhybernate.utils.ServiceResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUserData(User user){
            userRepository.save(user);
    }


    /**
     * A create operation, validates user informtion and adds new users to the database
     * Method first checks if the user is unique by checking if their email and pone number
     * already exists in the database, It also hashes the password before saving the dails to
     * the datbase
     * @param user
     * @return DataBaseResponse object
     */
    @Override
    public ServiceResponse userRegistration(User user){
        if(userEmailExists(user.getEmail())){
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Email Already Exists").setStatus(false).build();
        }

        if(userMobileExists(user.getMobile())){
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Mobile Number Already Exists").setStatus(false).build();
        }

        String PASSWORD_HASH = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(PASSWORD_HASH);
        saveUserData(user);
        return new ServiceResponse.ServiceResponseBuilder().setCode(200)
                .setMessage("Success").setStatus(true).build();
    }


    /**
     * Takes in user login credentials and compares it to database records
     * Method also compares the hashed password in the database to the user
     * provided password
     * @param userSignIn
     * @return DataBaseResponse object
     */
    @Override
    public ServiceResponse userLogin(UserSignIn userSignIn){
       User user =  getUserByEmail(userSignIn.getEmail());

       if(user == null){
           user = getUserByMobile(userSignIn.getEmail());
       }
        if(user == null){
           return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                   .setMessage("Invalid Email/Mobile Number").setStatus(false).build();
        }
        if(null == user.getPassword() || !user.getPassword().startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        if(!BCrypt.checkpw(userSignIn.getPassword(), user.getPassword() )) {
            return new ServiceResponse(false, "Invalid Password", 202);
        }

        return new ServiceResponse(true, "Success", 200, user);
    }

    /**
     * Checks if user provided email already exists in the database
     * @param email
     * @return a boolean
     *
     */
    @Override
    public boolean userEmailExists(String email) {
         List<User> users =  userRepository.findByEmail(email);
         return users.size() > 0;
    }

    /**
     * Checks if user provided mobile number already exists in the database
     * @param mobile
     * @return boolean
     */
    @Override
    public boolean userMobileExists(String mobile) {
        List<User> users =  userRepository.findByMobile(mobile);
        return users.size() > 0;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users =  userRepository.findByEmail(email);
        if(users.size() == 0) return null;
        return users.get(0);
    }

    @Override
    public User getUserByMobile(String mobile) {
        List<User> users =  userRepository.findByMobile(mobile);
        if(users.size() == 0) return null;
        return users.get(0);
    }
}
