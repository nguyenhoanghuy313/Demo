package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListManagerServlet", value = "/UserListManagerServlet")
public class UserListManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO ud = new UserDAO();
        List<User> userList = ud.getAllUser();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userListManager.jsp").forward(request, response);

    }
}
