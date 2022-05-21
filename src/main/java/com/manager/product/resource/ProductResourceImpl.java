package com.manager.product.resource;

import com.manager.product.dto.ProductDTO;
import com.manager.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductResourceImpl implements ProductResource {

    private final ProductService service;

    @Autowired
    public ProductResourceImpl(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products/")
    @Override
    public List<ProductDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/products/{id}")
    @Override
    public ProductDTO getOne(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/products")
    @Override
    public ProductDTO save(@Valid @RequestBody ProductDTO product) {
        return service.save(product);
    }

    @PutMapping("/products/{id}")
    @Override
    public ProductDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody ProductDTO product
    ) {
        return service.update(id, product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
    }
}
