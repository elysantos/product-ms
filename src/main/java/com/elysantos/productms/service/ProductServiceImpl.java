package com.elysantos.productms.service;

import com.elysantos.productms.exceptions.ProductInvalidException;
import com.elysantos.productms.mapper.ProductMapper;
import com.elysantos.productms.model.Product;
import com.elysantos.productms.model.dto.ProductRequestDTO;
import com.elysantos.productms.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductMapper productMapper;

    @Override
    public Product add(ProductRequestDTO productRequestDTO) {
        Product product = Product.builder()
                .id(UUIDUtils.get())
                .name(productRequestDTO.name)
                .description(productRequestDTO.description)
                .price(productRequestDTO.price)
                .build();
        productMapper.insert(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Product update(String id, ProductRequestDTO product)  {
        Product productFound = findOne(id);

        productFound.setName(product.name);
        productFound.setDescription(product.description);
        productFound.setPrice(product.price);

        productMapper.update(id, productFound);
        return productFound;
    }

    @Override
    public Product findOne(String id)  {
        Product product = productMapper.findById(id);
        if(product == null){
            throw new ProductInvalidException("Produto com id fornecido não encontrado");
        }
        return product;
    }

    @Override
    public List<Product> findByParams(String q, BigDecimal minPrice, BigDecimal maxPrice) {
        return productMapper.findByParams(q, minPrice, maxPrice);
    }

    @Override
    public Product delete(String id)  {
        Product productFound = findOne(id);
        productMapper.delete(id);
        return productFound;
    }
}
