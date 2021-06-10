package com.elysantos.productms.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequestDTO implements Serializable {
    @NotNull(message = "name não pode ser nulo")
    public String name;
    @NotNull(message = "description não pode ser nulo")
    public String description;
    @NotNull(message = "price não pode ser nulo")
    @PositiveOrZero(message = "price não pode ser negativo")
    public BigDecimal price;
}
