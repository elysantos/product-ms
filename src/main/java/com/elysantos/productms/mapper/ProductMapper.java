package com.elysantos.productms.mapper;

import com.elysantos.productms.model.Product;
import com.elysantos.productms.model.dto.ProductRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    void insert(@Param("product") Product product);

    void update(@Param("id") String id, @Param("product") Product product);

    void delete(@Param("id") String id);

    List<Product> findAll();

    Product findById(@Param("id") String id);

    List<Product> findByParams(@Param("q") String q,
                               @Param("minPrice") BigDecimal minPrice,
                               @Param("maxPrice") BigDecimal maxPrice);


}
