package com.manager.product.resource;

import com.manager.product.config.SwaggerConfig;
import com.manager.product.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {SwaggerConfig.PRODUCT_TAG})
public interface ProductResource {

    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    List<ProductDTO> findAll();

    @ApiOperation(value = "Search a product with an ID", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved product"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    ProductDTO getOne(@PathVariable("id") Long id);

    @ApiOperation(value = "Add a product", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved product"),
            @ApiResponse(code = 401, message = "You are not authorized to saved the resource")
    })
    ProductDTO save(@Valid @RequestBody ProductDTO product);

    @ApiOperation(value = "Update a product", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated product"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to save is not found")
    })
    ProductDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody ProductDTO product
    );

    @ApiOperation(value = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted product"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    ResponseEntity delete(@PathVariable("id") Long id);

}
