package com.elysantos.productms.service;

import com.elysantos.productms.exceptions.ProductInvalidException;
import com.elysantos.productms.model.Product;
import com.elysantos.productms.model.dto.ProductRequestDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    Product add(ProductRequestDTO product);

    List<Product> findAll();

    Product update(String id, ProductRequestDTO product);

    Product findOne(String id);

    List<Product> findByParams(String q, BigDecimal minPrice, BigDecimal maxPrice);

    Product delete(String id);
}
