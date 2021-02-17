package ru.geekbrains.listeners;

import ru.geekbrains.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepo productRepo = new ProductRepo();
        UserRepo userRepo = new UserRepo();
        CategoryRepo categoryRepo = new CategoryRepo();

        productRepo.saveOrUpdate(new Product(null,"Product 1","Description of product 1",new BigDecimal(100)));
        productRepo.saveOrUpdate(new Product(null,"Product 2","Description of product 2",new BigDecimal(200)));
        productRepo.saveOrUpdate(new Product(null,"Product 3","Description of product 3",new BigDecimal(300)));

        categoryRepo.saveOrUpdate(new Category(null, "Category 1",0l));
        categoryRepo.saveOrUpdate(new Category(null, "Category 12",1l));
        categoryRepo.saveOrUpdate(new Category(null, "Category 13",1l));

        userRepo.saveOrUpdate(new User(null,"mail1@mail.com","pass1","firstname 1", "lastname 1"));
        userRepo.saveOrUpdate(new User(null,"mail2@mail.com","pass2","firstname 2", "lastname 2"));
        userRepo.saveOrUpdate(new User(null,"mail3@mail.com","pass3","firstname 3", "lastname 3"));

        sce.getServletContext().setAttribute("productRepo",productRepo);
        sce.getServletContext().setAttribute("userRepo",userRepo);
        sce.getServletContext().setAttribute("categoryRepo",categoryRepo);
    }
}
