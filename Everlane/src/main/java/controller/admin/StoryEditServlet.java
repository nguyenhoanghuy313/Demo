package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Story;
import model.StoryDAO;

import java.io.IOException;

@WebServlet(name = "StoryEditServlet", value = "/StoryEditServlet")
public class StoryEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xStoryID = request.getParameter("StoryID").trim();
        StoryDAO sd = new StoryDAO();
        Story getStory = sd.getStoryByID(xStoryID);
        request.setAttribute("story", getStory);
        request.getRequestDispatcher("editStory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xStoryID = request.getParameter("StoryID").trim();
        String xStoryTitle = request.getParameter("storytitle").trim();
        String xStoryThumbnail = request.getParameter("storythumbnail").trim();
        String xStoryContent = request.getParameter("storycontent").trim();
        StoryDAO sd = new StoryDAO();
        sd.updateStory(xStoryID, xStoryThumbnail, xStoryTitle, xStoryContent);
        response.sendRedirect("StoryServlet?input=all");

    }
}
