package com.facebook.facebookhybernate.service;

import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.models.UserSignIn;
import com.facebook.facebookhybernate.repos.UserRepository;
import com.facebook.facebookhybernate.utils.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void saveUserData(User user);
    public boolean userEmailExists(String email);
    public boolean userMobileExists(String mobile);
    public User getUserById(long id);
    public User getUserByEmail(String email);
    public User getUserByMobile(String email);
    public ServiceResponse userLogin(UserSignIn userSignIn);
    public ServiceResponse userRegistration(User user);
}
