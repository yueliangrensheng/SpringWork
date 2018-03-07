package com.yazao.spring.servlet;

import com.yazao.spring.action.PDFAction;
import com.yazao.spring.utils.DependencyInjector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 生成 pdf 文件
 * Created by zhaishaoping on 06/03/2018.
 */
@WebServlet(name = "PDFServlet", urlPatterns = {"/pdf", "/form"})
public class PDFServlet extends HttpServlet {

    private DependencyInjector dependencyInjector;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dependencyInjector = new DependencyInjector();
        dependencyInjector.start();
    }

    @Override
    public void destroy() {
        if (dependencyInjector != null) {
            dependencyInjector.shutDown();
        }
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);

        if ("form".equals(action)) {
            String dispatchUrl = "/product/Form.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchUrl);
            dispatcher.forward(request, response);
            return;
        } else if ("pdf".equals(action)) {
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            String text = request.getParameter("text");
            System.out.println("text = " + text);

            String path = request.getServletContext().getRealPath("/result") + sessionId + ".pdf";
            if (dependencyInjector != null) {
                PDFAction pdfAction = (PDFAction) dependencyInjector.getObject(PDFAction.class);
                pdfAction.createPDF(path, text);
            }

            StringBuilder redirect = new StringBuilder();
            redirect.append(request.getScheme() + "://");
            redirect.append(request.getLocalName());
            int port = request.getLocalPort();
            if (port != 80) {
                redirect.append(":" + port);
            }
            String contextPath = request.getContextPath();
            if (!"/".equals(contextPath)) {
                redirect.append(contextPath);
            }
            redirect.append("/result" + sessionId + ".pdf");
            System.out.println("redirect = " + redirect.toString());
            response.sendRedirect(redirect.toString());
            return;
        }


    }
}
