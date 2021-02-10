package ru.geekbrains.utilServlets;

import ru.geekbrains.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet (urlPatterns = "/list-servlet")
public class PageListServlet extends HttpServlet{

    static final List<Page> PAGE_LIST = new LinkedList<>();
    static final StringBuilder listHtml = new StringBuilder();
    static final String tdAPrefix = "<td width=\"100px\"><a href=\"";
    static final String aPrefixEnd = "\">";
    static final String tdAPostfix = "</a></td>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Integer sessionCounter = (Integer) session.getAttribute("sessionCounter");
        if(sessionCounter == null){
            sessionCounter = 1;
            PAGE_LIST.add(new Page("main", "Главная страница"));
            PAGE_LIST.add(new Page("catalog", "Каталог"));
            PAGE_LIST.add(new Page("cart", "Корзина"));
            PAGE_LIST.add(new Page("order", "Заказ"));
            PAGE_LIST.add(new Page("product", "Товар"));
        }else sessionCounter++;
        session.setAttribute("sessionCounter",sessionCounter);

        String pageHeader = (String)req.getAttribute("pageHeader");

        if(listHtml.length()> 0) listHtml.delete(0,listHtml.length());
        listHtml.append("<table border = \"1px\" height=\"50px\"><tr>");
        for (Page page : PAGE_LIST) {
            if(!page.getRuLocale().equals(pageHeader)) {
                listHtml.append(String.format("%s%s%s%s%s",tdAPrefix,page.getRoot(),aPrefixEnd,page.getRuLocale(),tdAPostfix));
            }
        }
        listHtml.append("</tr></table>");

        resp.getWriter().println(listHtml);
    }
}
