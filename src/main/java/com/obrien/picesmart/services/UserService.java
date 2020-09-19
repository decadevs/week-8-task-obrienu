package com.obrien.picesmart.services;

import com.obrien.picesmart.model.User;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;
import com.obrien.picesmart.utils.ServiceResponse;

public interface UserService {
    ServiceResponse registerUser(UserReceivingDTO userReceivingDTO);
    ServiceResponse userLogin(SignInReceivingDTO signInReceivingDTO);
    UserSendingDTO getUserSendingDTOById(long id);
    User getUserById(long id);
    User editUser(UserReceivingDTO userReceivingDTO, long userId);
    void deleteUser(long userId);
}