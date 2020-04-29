package ua.nure.borisenko.practice9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class Part1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String op = req.getParameter("op");
        String res = "";
        if (op.equals("minus")) {
            res = String.valueOf(Integer.parseInt(x) - Integer.parseInt(y));
        } else if (op.equals("plus")) {
            res = String.valueOf(Integer.parseInt(x) + Integer.parseInt(y));
        }

        req.setAttribute("res", res);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!DOCTYPE HTML>");
        resp.getWriter().println("<html><body><p>" + res + "</p></body></html>");
    }
}
