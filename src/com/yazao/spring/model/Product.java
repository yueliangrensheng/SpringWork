package com.yazao.spring.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhaishaoping on 03/03/2018.
 */
public class Product implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
