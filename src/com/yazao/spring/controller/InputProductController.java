package com.yazao.spring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhaishaoping on 07/03/2018.
 */
public class InputProductController implements Controller {

    private Log logger = LogFactory.getFactory().getInstance(this.getClass());

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        logger.info(this.getClass().getSimpleName() + " called");

        //返回结果： 视图路径。
        //请求被转发到 /jsp/ProductForm.jsp 页面。（这里是 重定向）
        // 配置 视图解析器后，这里直接添加 "ProductForm"
        return new ModelAndView("ProductForm");
    }
}
