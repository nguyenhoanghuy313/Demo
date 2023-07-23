package controller.sale;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "OrderListManagerServlet", value = "/OrderListManagerServlet")
public class OrderListManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("UserID").trim();
        String userName = request.getParameter("UserName").trim();
        request.setAttribute("userO", userID);
        request.setAttribute("userOname", userName);
        request.getRequestDispatcher("orderListManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
