package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageServlet", urlPatterns = {"/", "home"})
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/page.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("Micheal") && password.equals("Jordan")) {
            request.setAttribute("validated", "validated");
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "error");
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        }
    }
}
