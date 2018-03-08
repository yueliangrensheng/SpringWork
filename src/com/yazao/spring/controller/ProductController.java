package com.yazao.spring.controller;

import com.yazao.spring.model.Product;
import com.yazao.spring.model.ProductForm;
import com.yazao.spring.service.ProductService;
import com.yazao.spring.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

/**
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/input-product"})
    public String inputProduct() {
        return "ProductForm";
    }

    @RequestMapping(value = {"/save-product"}, method = RequestMethod.POST)
    public String saveProduct(ProductForm productForm, Model model, RedirectAttributes redirectAttributes) {
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
        Product saveProduct = productService.add(product);

        redirectAttributes.addFlashAttribute("message", "The Product was successfully added.");
        return "redirect:/view-product/" + saveProduct.getId();
    }


    @RequestMapping(value = {"/view-product/{id}"})
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }

}
