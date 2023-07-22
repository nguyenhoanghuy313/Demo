package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.Category;
import model.CategoryDAO;
import entity.User;
import model.UserDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffListManagerServlet", value = "/StaffListManagerServlet")
public class StaffListManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO ud = new UserDAO();
        String role = request.getParameter("role");
        System.out.println(role);
        List<User> userList = ud.getUsersByRoleAndSort(role);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("staffListManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xRole = request.getParameter("role").trim();
        String xName = request.getParameter("userName").trim();
        UserDAO ud = new UserDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Category> cateList = cd.getAllCategory();
        request.setAttribute("cateList", cateList);
        List<User> userList = ud.searchByName(xName, xRole);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("staffListManager.jsp").forward(request, response);
    }
}
