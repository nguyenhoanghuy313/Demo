package controller.admin;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
@WebServlet(name = "deleteUser", value = "/DeleteUser")

public class DeleteUser extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String delUserID = request.getParameter("UserID");
        UserDAO ud = new UserDAO();
        ud.deleteUser(delUserID);
        response.sendRedirect("UserListManagerServlet?role=all");
    }
}
