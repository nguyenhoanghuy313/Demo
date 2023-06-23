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
        String role = request.getParameter("role");
        System.out.println(role);
        List<User> userList = ud.getUsersByRoleAndSort(role);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userListManager.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xName = request.getParameter("userName").trim();
        UserDAO ud = new UserDAO();
        CategoryDAO cd = new CategoryDAO();
//        String cateID = request.getParameter("categoryID");
        List<Category> cateList = cd.getCategory();
        request.setAttribute("cateList", cateList);
        List<User> userList = ud.searchByName(xName);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userListManager.jsp").forward(request, response);
    }
}
