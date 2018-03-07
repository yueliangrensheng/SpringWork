package com.yazao.spring.servlet;


import com.yazao.spring.action.ProductValidator;
import com.yazao.spring.action.SaveProductAction;
import com.yazao.spring.model.Product;
import com.yazao.spring.model.ProductForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaishaoping on 03/03/2018.
 */
//@WebServlet(name = "ControllerServlet", urlPatterns = {
//        "/input-product", "/save-product"})
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * 此方法： 处理用户输入请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        if ("input-product".equals(action)) {
            dispatchUrl = "/jsp/ProductForm.jsp";
        } else if ("save-product".equals(action)) {
            ProductForm productForm = new ProductForm();
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));

            ProductValidator validator = new ProductValidator();
            List<String> errors = validator.validate(productForm);


            if (errors.isEmpty()) {

                Product product = new Product();
                product.setName(productForm.getName());
                product.setDescription(productForm.getDescription());
                try {
                    product.setPrice(new BigDecimal(productForm.getPrice()));
                } catch (Exception e) {
                }

                //保存数据
                SaveProductAction saveProductAction = new SaveProductAction();
                saveProductAction.save(product);
                //将 Product 对想保存到 request域中，为了 jsp页面可以访问该对象。
                request.setAttribute("product", product);
                dispatchUrl = "/jsp/ProductDetails.jsp";
            } else {
                request.setAttribute("errors", errors);
                request.setAttribute("form", productForm);
                dispatchUrl = "/jsp/ProductForm.jsp";
            }


        }

        if (dispatchUrl != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
        }


    }
}
