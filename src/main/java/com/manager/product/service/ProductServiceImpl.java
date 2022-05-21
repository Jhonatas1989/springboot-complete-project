package com.manager.product.service;

import com.manager.product.dto.ProductDTO;
import com.manager.product.entity.Product;
import com.manager.product.exception.ProductNotFoundException;
import com.manager.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> products = repository
                .findAll()
                .stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        if (products.isEmpty())
            throw new ProductNotFoundException("product.not.found");

        return products;
    }

    @Override
    public ProductDTO findById(long id) {
        Product product = repository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product.not.found"));

        return this.convertToProductDTO(product);
    }

    @Override
    public ProductDTO save(ProductDTO product) {
        Product productWillBeSaved = this.convertToProduct(product);

        Product productSaved = repository.save(productWillBeSaved);

        return this.convertToProductDTO(productSaved);
    }

    @Override
    public ProductDTO update(long id, ProductDTO product) {
        Product storedProduct = repository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product.not.found"));

        storedProduct.setName(product.getName());
        storedProduct.setPrice(product.getPrice());

        Product productWillBeUpdated = this.convertToProduct(product);

        Product productSUpdated = repository.save(productWillBeUpdated);

        return this.convertToProductDTO(productSUpdated);
    }

    @Override
    public void delete(long id) {
        Product product = repository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("product.not.found"));

        repository.delete(product);
    }

    private Product convertToProduct(ProductDTO product) {
        return modelMapper.map(product, Product.class);
    }

    private ProductDTO convertToProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
