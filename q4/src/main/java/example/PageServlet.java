package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "PageServlet", urlPatterns = {"/", "page1"})
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Double total = (Double) session.getAttribute("page1total");
        if (total == null) {
            total = 0.0;
            session.setAttribute("page1total", total);
        }

        Map<String, Double> items = Items.getPage1Items();

        request.setAttribute("total", total);
        request.setAttribute("items", items.keySet());
        request.getRequestDispatcher("/page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item = request.getParameter("item");

        Map<String, Double> items = Items.getPage1Items();

        HttpSession session = request.getSession(true);
        Integer count = (Integer) session.getAttribute(item);
        if (count == null) {
            session.setAttribute(item, 1);
        } else {
            session.setAttribute(item, count+1);
        }

        Double total = (Double) session.getAttribute("page1total");
        total += items.get(item);
        session.setAttribute("page1total",  total);

        request.setAttribute("total", total);
        request.setAttribute("items", items.keySet());
        request.getRequestDispatcher("/page.jsp").forward(request, response);
    }
}
