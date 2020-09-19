package com.obrien.picesmart.services.impl;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.repository.BrandRepository;
import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.utils.DTO.BrandReceivingDTO;
import com.obrien.picesmart.utils.DTO.BrandSendingDTO;
import com.obrien.picesmart.utils.DTO.SignInReceivingDTO;
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
class BrandServiceImplTest {
    @Autowired
    BrandService brandService;
    @Autowired
    BrandRepository brandRepository;
    BrandReceivingDTO brandReceivingDTO = new BrandReceivingDTO();

    @BeforeEach
    void init(){
        brandReceivingDTO.setAbout("Hello there");
        brandReceivingDTO.setAddress("30 Asanjo");
        brandReceivingDTO.setBrandName("Decagon");
        brandReceivingDTO.setEmail("decagon@decagon.hq");
        brandReceivingDTO.setPassword("decagon");
        brandReceivingDTO.setMobile("09089898987");
    }

    @Test
    void registerBrand() {
        brandService.registerBrand(brandReceivingDTO);
        List<Brand> brands = brandRepository.findAll();
        assertEquals(1, brands.size(), "Should return a brand after registration");

    }

    @Test
    void brandLogin() {
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("decagon@decagon.hq", "decagon");
        brandService.registerBrand(brandReceivingDTO);
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        BrandSendingDTO brandSendingDTO  = (BrandSendingDTO) response.getData();
        assertTrue(response.isStatus(), "Should login successfully");
        assertEquals("Decagon",brandSendingDTO.getBrandName(), "Should return correct brand");
    }

    @Test
    void brandLoginWithMobile() {
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("09089898987", "decagon");
        brandService.registerBrand(brandReceivingDTO);
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        BrandSendingDTO brandSendingDTO  = (BrandSendingDTO) response.getData();
        assertTrue(response.isStatus(), "Should login successfully");
        assertEquals("Decagon",brandSendingDTO.getBrandName(), "Should return correct brand");
    }

    @Test
    void brandLoginWithInvalidDetail() {
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("09898987", "decagon");
        brandService.registerBrand(brandReceivingDTO);
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        assertFalse(response.isStatus(), "Should not login");
        assertEquals("Invalid Mobile Number/Email",response.getMessage(), "Should return correct message");
    }

    @Test
    void brandLoginWithInvalidPassword() {
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("09089898987", "decon");
        brandService.registerBrand(brandReceivingDTO);
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        assertFalse(response.isStatus(), "Should not login");
        assertEquals("Invalid Password",response.getMessage(), "Should return correct message");
    }

    @Test
    void findBrandById() {
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("09089898987", "decagon");
        brandService.registerBrand(brandReceivingDTO);
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        BrandSendingDTO brandSendingDTO  = (BrandSendingDTO) response.getData();
        Brand brand  = brandService.getBrandById(brandSendingDTO.getId());
        assertEquals(brandSendingDTO.getBrandName(),brand.getBrandName(), "Should return correct brand");
    }

    @Test
    void updateBrand(){
        brandService.registerBrand(brandReceivingDTO);
        SignInReceivingDTO signInReceivingDTO = new SignInReceivingDTO("09089898987", "decagon");
        ServiceResponse response = brandService.brandLogin(signInReceivingDTO);
        BrandSendingDTO brandSendingDTO  = (BrandSendingDTO) response.getData();


    }

}