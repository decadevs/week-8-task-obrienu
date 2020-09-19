package com.obrien.picesmart.services;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.utils.DTO.BrandReceivingDTO;
import com.obrien.picesmart.utils.DTO.BrandSendingDTO;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
import com.obrien.picesmart.utils.ServiceResponse;

public interface BrandService {
    ServiceResponse registerBrand(BrandReceivingDTO brandData);
    ServiceResponse brandLogin(SignInReceivingDTO signInReceivingDTO);
    BrandSendingDTO getBrandSendingDTOById(long id);
    Brand getBrandById(long id);
    void updateBrand(BrandReceivingDTO brandReceivingDTO, long brandId);
    void deleteUser(long id);
}
