package com.elysantos.productms.controller;

import com.elysantos.productms.mapper.ProductMapper;
import com.elysantos.productms.model.Product;
import com.elysantos.productms.model.dto.ProductRequestDTO;
import com.elysantos.productms.service.IProductService;
import com.elysantos.productms.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ProductMapper productMapper;
    private IProductService productService;
    private ProductController productController;

    private static final String ENDPOINT = "/products";
    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    void setUp() {
        productMapper = mock(ProductMapper.class);
        productService = new ProductServiceImpl(productMapper);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductController(productService))
                .build();
    }

    @Test
    void testCreateASuccessfulProduct() throws Exception {
        ProductRequestDTO productRequestDTO =new ProductRequestDTO();
        productRequestDTO.name = "Name";
        productRequestDTO.description = "Descrição";
        productRequestDTO.price = BigDecimal.ONE;
        doNothing().when(productMapper).insert(any());
        mockMvc.perform(post(ENDPOINT)
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(productRequestDTO))
        ).andExpect(status().isCreated());

        verify(productMapper, times(1)).insert(any());
    }

    @Test
    void testCreateABadRequerstProduct() throws Exception {
        ProductRequestDTO productRequestDTO =new ProductRequestDTO();
        productRequestDTO.name = "Name";
        productRequestDTO.price = BigDecimal.ONE;
        doNothing().when(productMapper).insert(any());
        mockMvc.perform(post(ENDPOINT)
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(productRequestDTO))
        ).andExpect(status().isBadRequest());

        verify(productMapper, times(0)).insert(any());
    }

    @Test
    void testUpdateSuccessful() throws Exception {
        ProductRequestDTO productRequestDTO =new ProductRequestDTO();
        productRequestDTO.name = "Name";
        productRequestDTO.description = "Descrição";
        productRequestDTO.price = BigDecimal.ONE;
        doNothing().when(productMapper).update(anyString(), any());
        when(productMapper.findById(anyString())).thenReturn(
                Product.builder().build()
        );

        mockMvc.perform(put(ENDPOINT + "/string-id")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(productRequestDTO))
        ).andExpect(status().isAccepted());

        verify(productMapper, times(1)).update(anyString(), any());
        verify(productMapper, times(1)).findById(any());
    }

    @Test
    void testUpdateBadRequest() throws Exception {
        ProductRequestDTO productRequestDTO =new ProductRequestDTO();
        productRequestDTO.name = "Name";
        productRequestDTO.price = BigDecimal.ONE;
        doNothing().when(productMapper).update(anyString(), any());
        when(productMapper.findById(anyString())).thenReturn(
                Product.builder().build()
        );

        mockMvc.perform(put(ENDPOINT + "/string-id")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(productRequestDTO))
        ).andExpect(status().isBadRequest());

        verify(productMapper, times(0)).update(anyString(), any());
        verify(productMapper, times(0)).findById(any());
    }

    @Test
    void testDeleteSuccessful() throws Exception {

        doNothing().when(productMapper).delete(any());
        when(productMapper.findById(anyString())).thenReturn(
                Product.builder().build()
        );

        mockMvc.perform(delete(ENDPOINT + "/string-id")
        ).andExpect(status().is2xxSuccessful());

        verify(productMapper, times(1)).delete(any());
        verify(productMapper, times(1)).findById(any());
    }

    @Test
    void testGetOneSuccessful() throws Exception {

        when(productMapper.findById(anyString())).thenReturn(
                Product.builder().build()
        );

        mockMvc.perform(get(ENDPOINT + "/string-id")
        ).andExpect(status().is2xxSuccessful());

        verify(productMapper, times(1)).findById(any());
    }


}
