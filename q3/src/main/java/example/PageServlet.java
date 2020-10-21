package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PageServlet", urlPatterns = {"/", "home"})
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cookie = findOrCreate(request, response);
        int value = Integer.parseInt(cookie.getValue());
        cookie.setValue(String.valueOf(value+1));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (value == 0) {
            out.print("Welcome!");
        } else {
            out.print("Visited " + value + " times");
        }
        out.close();
    }

    private Cookie findOrCreate(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("countCookie")) {
                return cookies[i];
            }
        }

        Cookie cookie = new Cookie("countCookie", "0");
        response.addCookie(cookie);
        return cookie;
    }
}
