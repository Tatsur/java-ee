package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {

    private ServletConfig servletConfig;
    private final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("MyServlet is initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Got request to MyServlet");
        servletResponse.getWriter().println("<h1>Hello World!<h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
