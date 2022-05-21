package com.manager.product.service;

import com.manager.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(long id);

    ProductDTO save(ProductDTO product);

    ProductDTO update(long id, ProductDTO product);

    void delete(long id);

}
