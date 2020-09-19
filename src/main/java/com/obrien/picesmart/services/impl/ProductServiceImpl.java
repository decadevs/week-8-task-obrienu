package com.obrien.picesmart.services.impl;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.model.Product;
import com.obrien.picesmart.repository.ProductRepository;
import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.utils.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandService brandService;

    public Product getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    @Override
    public ProductDTO getProductDTO(long id) {
        List<Object[]> products = productRepository.findProduct(id);
        Object[] product = products.get(0);
        if (product == null) {
            return null;
        }
        ProductDTO productDTO;
        Brand brand;
        productDTO = new ProductDTO();
        brand = new Brand();

        productDTO.setId(convertNum((BigInteger) product[0]));
        productDTO.setDescription(converClob(product[1]));
        productDTO.setImageUrl((String) product[2]);
        productDTO.setPrice((int) product[3]);
        productDTO.setCategory((String) product[4]);
        productDTO.setCatchPhrase((String) product[5]);
        productDTO.setAvgRating(convertNum((BigDecimal) product[6]));
        productDTO.setTotalReviews(convertNum((BigInteger) product[7]));
        brand.setId(convertNum((BigInteger) product[8]));
        brand.setBrandName((String) product[9]);
        productDTO.setName((String) product[10]);
        productDTO.setBrand(brand);
        return productDTO;
    }


    @Override
    public void saveProduct(ProductDTO productDTO, long brandId) {
        Product newProduct = new Product();
        Brand brand = brandService.getBrandById(brandId);
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setName(productDTO.getName());
        newProduct.setImageUrl(productDTO.getImageUrl());
        newProduct.setCatchPhrase(productDTO.getCatchPhrase());
        newProduct.setCategory(productDTO.getCategory());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setBrand(brand);
        productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(ProductDTO productDTO, long productId) {
        Product product = getProduct(productId);
        String name = productDTO.getName();
        String description = productDTO.getDescription();
        String imageUrl = productDTO.getImageUrl();
        String category = productDTO.getCategory();
        int price = productDTO.getPrice();
        String catchPhrase = product.getCatchPhrase();

        if (!name.isBlank()) product.setName(name);
        if (!description.isBlank()) product.setDescription(description);
        if (!imageUrl.isBlank()) product.setImageUrl(imageUrl);
        if (category.isBlank()) product.setCategory(category);
        if (price != 0) product.setPrice(price);
        if (!catchPhrase.isBlank()) product.setCatchPhrase(catchPhrase);
        productRepository.save(product);
    }

    @Override
    /**
     * @param pageNumber
     * @param size
     * @return
     */
    public List<ProductDTO> getProducts(int page, int size) {
        int offset = size * page;
        List<ProductDTO> products = new ArrayList<>();
        System.out.println("OFFSET =" + offset + " LIMIT = " + size);
        List<Object[]> listOfProducts = productRepository.findProducts(size, offset);
        ProductDTO productDTO;
        Brand brand;
        for (Object[] product : listOfProducts) {
            productDTO = new ProductDTO();
            brand = new Brand();
            productDTO.setId(convertNum((BigInteger) product[0]));
            productDTO.setDescription(converClob(product[1]));
            productDTO.setImageUrl((String) product[2]);
            productDTO.setPrice((int) product[3]);
            productDTO.setCategory((String) product[4]);
            productDTO.setCatchPhrase((String) product[5]);
            productDTO.setAvgRating(convertNum((BigDecimal) product[6]));
            productDTO.setTotalReviews(convertNum((BigInteger) product[7]));
            brand.setId(convertNum((BigInteger) product[8]));
            brand.setBrandName((String) product[9]);
            productDTO.setName((String) product[10]);

            productDTO.setBrand(brand);

            products.add(productDTO);
        }
        return products;
    }

    @Override
    public List<ProductDTO> getBrandProducts(long brandId, int page, int size) {
        int offset = size * page;
        List<ProductDTO> products = new ArrayList<>();
        List<Object[]> listOfProducts = productRepository.findProductsByBrand(brandId, size, offset);
        ProductDTO productDTO;
        Brand brand;
        for (Object[] product : listOfProducts) {
            productDTO = new ProductDTO();
            brand = new Brand();
            productDTO.setId(convertNum((BigInteger) product[0]));
            productDTO.setDescription(converClob(product[1]));
            productDTO.setImageUrl((String) product[2]);
            productDTO.setPrice((int) product[3]);
            productDTO.setCategory((String) product[4]);
            productDTO.setCatchPhrase((String) product[5]);
            productDTO.setAvgRating(convertNum((BigDecimal) product[6]));
            productDTO.setTotalReviews(convertNum((BigInteger) product[7]));
            brand.setId(convertNum((BigInteger) product[8]));
            brand.setBrandName((String) product[9]);
            productDTO.setName((String) product[10]);

            productDTO.setBrand(brand);

            products.add(productDTO);
        }
        return products;
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    private String converClob(Object myClob){
        Clob clob =  (Clob) myClob;
        String description = "";
        try {
            description = clob.getSubString(1, (int) clob.length());
        } catch (SQLException e) {
            e.printStackTrace();
            return description;
        }
        System.out.println(description);
        return description;
    }

    private Double convertNum(BigDecimal num) {
        return num.doubleValue();
    }


    private Integer convertNum(BigInteger num) {
        return num.intValue();
    }

}
