package com.elysantos.productms.controller;

import com.elysantos.productms.exceptions.ProductInvalidException;
import com.elysantos.productms.model.Product;
import com.elysantos.productms.model.dto.ProductRequestDTO;
import com.elysantos.productms.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;

    @Operation(summary = "Creates a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a product"),
            @ApiResponse(responseCode = "400", description = "Unable to create a product due to request error", content = {}),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(produces = "application/json")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDTO requestProduct){
        Product productCreated = iProductService.add(requestProduct);
        return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
    }

    @Operation(summary = "Update product from id provided")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @Valid @RequestBody ProductRequestDTO product) throws ProductInvalidException {
        Product prod= iProductService.update(id, product);
        return new ResponseEntity<>(prod, HttpStatus.ACCEPTED);

    }

    @Operation(summary = "Get product from id")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) throws ProductInvalidException {
        Product product = iProductService.findOne(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Search products using params")
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("q") String q,
                                                 @RequestParam("min_price") BigDecimal minPrice,
                                                 @RequestParam("max_price") BigDecimal maxPrice){
        List<Product> products = iProductService.findByParams(q, minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Get all products saved")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> products = iProductService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Delete product if the id is valid")
    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) throws ProductInvalidException {
        Product product = iProductService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
