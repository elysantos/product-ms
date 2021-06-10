package com.elysantos.productms.controller;

import com.elysantos.productms.exceptions.ProductInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(assignableTypes = ProductController.class)
public class ProductControllerExceptionHandler {

    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            NotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleException(Exception ex){
        log.error("Erro na criação de produto: {} ", "faltam parametros");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status_code", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductInvalidException.class)
    public ResponseEntity<Object> handleProductException(ProductInvalidException ex){
        log.error("Erro na localização de produto: {} ", "faltam parametros");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status_code", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
