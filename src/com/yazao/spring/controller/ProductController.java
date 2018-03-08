package com.yazao.spring.controller;

import com.yazao.spring.model.Product;
import com.yazao.spring.model.ProductForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

/**
 */
@Controller
public class ProductController {

    @RequestMapping(value = {"/input-product"})
    public String inputProduct() {
        return "ProductForm";
    }

    @RequestMapping(value = {"/save-product"})
    public String saveProduct(ProductForm productForm, Model model) {
        // 关于 org.springframework.ui.Model这个类
        //Spring MVC 都会在每一个请求处理方法被调用时创建一个Model实例，用于增加需要在视图中的属性。

        //这里就不再需要创建 ProductForm 示例对象了。
        // create model
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(new BigDecimal(productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        //add Product
        model.addAttribute(product);//作用：用于在视图中显示属性值。 这里就好比是 在 HttpRequestServlet 域中添加 product 实例。
        return "ProductDetails";
    }
}
