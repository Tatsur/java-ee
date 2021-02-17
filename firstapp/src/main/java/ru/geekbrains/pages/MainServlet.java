package ru.geekbrains.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader","Главная страница");
        getServletContext().getRequestDispatcher("/page_header").include(req,resp);
        getServletContext().getRequestDispatcher("/list-servlet").include(req,resp);
        getServletContext().getRequestDispatcher("/product").forward(req,resp);
    }
}
