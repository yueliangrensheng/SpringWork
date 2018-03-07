package com.yazao.spring.controller;

import com.yazao.spring.model.Product;
import com.yazao.spring.model.ProductForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by zhaishaoping on 07/03/2018.
 */
public class SaveProductController implements Controller {

    private Log logger = LogFactory.getFactory().getInstance(this.getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info(this.getClass().getSimpleName() + " called");

        ProductForm productForm = new ProductForm();
        // populate action properties
        productForm.setName(request.getParameter("name"));
        productForm.setDescription(request.getParameter("description"));
        productForm.setPrice(request.getParameter("price"));

        // create model
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(new BigDecimal(productForm.getPrice()));
        } catch (NumberFormatException e) {
        }
        //返回结果： 视图路径、模型名称 和 模型。 该模型将提供给目标视图，用于界面显示。
        //请求被转发到 /jsp/ProductForm.jsp 页面。（这里是 重定向）
        // 配置 视图解析器后，这里直接添加 "ProductDetails"
        return new ModelAndView("ProductDetails", "product", product);
    }
}
