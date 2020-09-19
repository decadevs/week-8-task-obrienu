package com.obrien.picesmart.services;

import com.obrien.picesmart.model.Product;
import com.obrien.picesmart.utils.DTO.ProductDTO;

import java.util.List;



public interface ProductService {
    public Product getProduct(long id);
    public void saveProduct(ProductDTO productDTO, long brandId);
    public void updateProduct(ProductDTO productDTO, long productId);
    public void deleteProduct(long id);
    public List<ProductDTO> getProducts(int pageNumber, int size);
    public List<ProductDTO> getBrandProducts(long brandId, int pageNumber, int size);
    public ProductDTO getProductDTO(long id);
}
