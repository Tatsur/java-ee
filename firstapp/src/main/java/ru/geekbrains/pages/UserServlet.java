package ru.geekbrains.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private static UserRepo userRepo;

    @Override
    public void init() throws ServletException {
        this.userRepo = (UserRepo) getServletContext().getAttribute("userRepo");
        if(userRepo == null){
            throw new ServletException("UserRepo not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        req.setAttribute("pageHeader","Пользователь");
        //getServletContext().getRequestDispatcher("/page_header").include(req,resp);
        //getServletContext().getRequestDispatcher("/list-servlet").include(req,resp);
        if(req.getPathInfo() == null || req.getPathInfo().equals("/")){
            req.setAttribute("users",userRepo.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
        }else if (req.getPathInfo().equals("/edit")){
            req.setAttribute("user",getUser(req,resp));
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req,resp);
        }else if(req.getPathInfo().equals("/delete")){
            userRepo.deleteById(getUser(req,resp).getId());
            req.setAttribute("users",userRepo.findAll());
            String str = String.format("%s%s",getServletContext().getContextPath(),"/user");
            logger.info(str);
            resp.sendRedirect(getServletContext().getContextPath() + "/user");

        }else if(req.getPathInfo().equals("/add")){
            req.setAttribute("user", new User());
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req,resp);
        }else  getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
    }
    private User getUser(HttpServletRequest req, HttpServletResponse resp){
        long id;
        try{
                id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            resp.setStatus(400);
            return null;
        }
        User user = userRepo.findById(id);
        if(user == null){
            resp.setStatus(404);
            return null;
        }
        return user;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            String idParam = req.getParameter("id");
            if(idParam != null && !idParam.isBlank()) {
                id = Long.parseLong(idParam);
            }
        }catch (NumberFormatException e){
            resp.setStatus(400);
            return;
        }
        User user = new User(id,req.getParameter("email"),req.getParameter("password"),req.getParameter("firstName"),req.getParameter("lastName"));
        userRepo.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }
}
