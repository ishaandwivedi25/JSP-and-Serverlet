package example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "BillServlet", urlPatterns = {"bill"})
public class BillServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Double page1total = (Double) session.getAttribute("page1total");
        Double page2total = (Double) session.getAttribute("page2total");
        if (page1total == null) {
            page1total = 0.0;
        }
        if (page2total == null) {
            page2total = 0.0;
        }

        Map<String, Double> page1Items = Items.getPage1Items();
        Map<String, Double> page2Items = Items.getPage2Items();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.write("<div>Page 1:<ul>");
        for (Map.Entry<String, Double> entry : page1Items.entrySet()) {
            Integer count = (Integer) session.getAttribute(entry.getKey());
            if (count != null) {
                out.print(String.format("<li>%s: %f x %d = %f</li>", entry.getKey(), entry.getValue(), count,
                        (count * entry.getValue())));
            }
        }
        out.write(String.format("</ul>Total: %f</div>", page1total));

        out.write("<div>Page 2:<ul>");
        for (Map.Entry<String, Double> entry : page2Items.entrySet()) {
            Integer count = (Integer) session.getAttribute(entry.getKey());
            if (count != null) {
                out.print(String.format("<li>%s: %f x %d = %f</li>", entry.getKey(), entry.getValue(), count,
                        (count * entry.getValue())));
            }
        }
        out.write(String.format("</ul>Total: %f</div>", page2total));

        out.close();
    }
}
