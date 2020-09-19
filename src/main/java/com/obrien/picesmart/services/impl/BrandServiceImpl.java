package com.obrien.picesmart.services.impl;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.repository.BrandRepository;
import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.utils.DTO.BrandReceivingDTO;
import com.obrien.picesmart.utils.DTO.BrandSendingDTO;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
import com.obrien.picesmart.utils.DTO.UserReceivingDTO;
import com.obrien.picesmart.utils.ServiceResponse;
import com.obrien.picesmart.utils.builders.BrandBuilder;
import com.obrien.picesmart.utils.builders.BrandSenderBuilder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class   BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;


    /**
     * Method creates and registers a new brand
     * It checks if brands email and mobile already exists in the database
     * It hashed the passwords then calls the brand builder which return a new
     * brand model which is then saved to the database
     * @param brandReceivingDTO
     * @return
     */
    @Override
    public ServiceResponse registerBrand(BrandReceivingDTO brandReceivingDTO) {
        if(emailExists(brandReceivingDTO.getEmail())){
                return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Email Already Exists").setStatus(false).build();
             }
        if(mobileExists(brandReceivingDTO.getMobile())) {
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Mobile Already Exists").setStatus(false).build();
        }

        String PASSWORD_HASH = BCrypt.hashpw(brandReceivingDTO.getPassword(), BCrypt.gensalt());
        Brand newBrand = new BrandBuilder().setBrand(brandReceivingDTO).setHash(PASSWORD_HASH).build();
        brandRepository.save(newBrand);
        return new ServiceResponse.ServiceResponseBuilder().setCode(200)
                    .setMessage("Success").setStatus(true).build();
    }

    /**
     * Method Handles brand login. It first searches for brand by email
     * and if no brand is returned it searches for brand by mobile number
     * if no brand with the inputted details is seen it returns returns an error message
     * else it checks if the returned brands password matches the hash code in database
     * it sends the user and a success message if it matches else it returns an error message
     * @param signInReceivingDTO
     * @return
     */
    @Override
    public ServiceResponse brandLogin(SignInReceivingDTO signInReceivingDTO) {
        Brand brand = getBrandByEmail(signInReceivingDTO.getDetails());
        if(brand == null){
            brand = getBrandByMobile(signInReceivingDTO.getDetails());
        }
        if(brand == null){
            return new ServiceResponse.ServiceResponseBuilder().setCode(202)
                    .setMessage("Invalid Mobile Number/Email").setStatus(false).build();
        }
        if(null == signInReceivingDTO.getPassword() || !brand.getHash().startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        if(!BCrypt.checkpw(signInReceivingDTO.getPassword(), brand.getHash() )) {
            return new ServiceResponse(false, "Invalid Password", 202);
        }

        BrandSendingDTO brandSendingDTO = new BrandSenderBuilder().setBrand(brand).build();
        return new ServiceResponse(true, "Success", 200, brandSendingDTO);
    }

    @Override
    public Brand getBrandById(long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(brand.isEmpty()){
            return null;
        }
        return brand.get();
    }

    @Override
    public void updateBrand(BrandReceivingDTO brandReceivingDTO, long brandId) {
        Brand brand = getBrandById(brandId);
        if(brandReceivingDTO.getEmail() != null) brand.setEmail(brandReceivingDTO.getEmail());
        if(brandReceivingDTO.getMobile() != null) brand.setMobile(brandReceivingDTO.getMobile());
        if(brandReceivingDTO.getAbout() != null) brand.setAbout(brandReceivingDTO.getAbout());
        if(brandReceivingDTO.getBrandName() != null) brand.setBrandName(brandReceivingDTO.getBrandName());
        if(brandReceivingDTO.getWebsite() != null) brand.setWebsite(brandReceivingDTO.getWebsite());
        if(brandReceivingDTO.getAddress() != null) brand.setAddress(brandReceivingDTO.getAddress());
        brandRepository.save(brand);
    }

    @Override
    public void deleteUser(long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandSendingDTO getBrandSendingDTOById(long id) {
        Brand brand = getBrandById( id);
        if(brand == null){
            return null;
        }
        BrandSendingDTO brandSendingDTO = new BrandSenderBuilder().setBrand(brand).build();
        return  brandSendingDTO;
    }




    private boolean emailExists(String email) {
        List<Brand> brands = brandRepository.findByEmail(email);
        if(brands.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean mobileExists(String mobile) {
        List<Brand> brands = brandRepository.findByMobile(mobile);
        if(brands.isEmpty()){
            return false;
        }
        return true;
    }

    private Brand getBrandByEmail(String email){
        List<Brand> brands = brandRepository.findByEmail(email);
        if(brands.isEmpty()){
            return null;
        }
        return brands.get(0);
    }

    private Brand getBrandByMobile(String mobile){
        List<Brand> brands = brandRepository.findByMobile(mobile);
        if(brands.isEmpty()){
            return null;
        }
        return brands.get(0);
    }
}
