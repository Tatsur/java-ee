package ru.geekbrains.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private static ProductRepo productRepo;

    @Override
    public void init() throws ServletException {
        this.productRepo = (ProductRepo) getServletContext().getAttribute("productRepo");
        if(productRepo == null){
            throw new ServletException("ProductRepo not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        req.setAttribute("pageHeader","Товар");
        getServletContext().getRequestDispatcher("/page_header").include(req,resp);
        getServletContext().getRequestDispatcher("/list-servlet").include(req,resp);
        if(req.getPathInfo() == null || req.getPathInfo().equals("/")){
            req.setAttribute("products",productRepo.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req,resp);
        }else if (req.getPathInfo().equals("/edit")){
            long id;
            try{
                id = Long.parseLong(req.getParameter("id"));
            }catch (NumberFormatException e){
                resp.setStatus(400);
                return;
            }
            Product product = productRepo.findById(id);
            if(product == null){
                resp.setStatus(404);
                return;
            }
            req.setAttribute("product",product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req,resp);
        }else if(req.getPathInfo().equals("/delete")){
            //TODO delete product
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.setStatus(400);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        }catch (NumberFormatException e){
            resp.setStatus(400);
            return;
        }
        Product product = new Product(id,req.getParameter("name"),req.getParameter("description"),price);
        productRepo.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }
}
