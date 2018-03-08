package com.yazao.spring.service;

import com.yazao.spring.model.Product;

/**
 * Created by zhaishaoping on 08/03/2018.
 */
public interface ProductService {
    Product add(Product product);

    Product get(long id);
}
