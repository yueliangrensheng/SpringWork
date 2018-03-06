package com.yazao.spring.action;

import com.yazao.spring.model.ProductForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaishaoping on 06/03/2018.
 */
public class ProductValidator {

    /**
     * 校验器
     * @param productForm
     * @return
     */
    public List<String> validate(ProductForm productForm) {
        List<String> errors = new ArrayList<>();
        if (productForm == null) {
            errors.add("Please add Product Info!!!");
        }

        String name = productForm.getName();
        String price = productForm.getPrice();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Product mast have a name");
        }
        if (price == null || price.trim().isEmpty()) {
            errors.add("Product mast have a price");
        }

        try {
            Float.parseFloat(price);
        } catch (Exception e) {
            errors.add("Invalid price value!!!");
        }


        return errors;
    }
}
