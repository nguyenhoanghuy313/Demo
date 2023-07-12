package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import java.util.*;

import java.io.IOException;

@WebServlet(name = "StoryServlet", value = "/StoryServlet")
public class StoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoryDAO sd = new StoryDAO();

        String input = request.getParameter("input").trim();

        if(input.equals("delete")) {
            String xStoryID = request.getParameter("StoryID").trim();
            sd.deleteStory(xStoryID);
            response.sendRedirect("StoryServlet?input=all");
        }
        else{
            List<Story> storyList= sd.getAllStory(input);
            request.setAttribute("storyList", storyList);
            request.getRequestDispatcher("storyListManager.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoryDAO sd = new StoryDAO();

        String input = request.getParameter("input").trim();

        if(input.equals("1")) {
            String xStoryTitle = request.getParameter("storytitle").trim();
            String xStoryThumbnail = request.getParameter("storythumbnail").trim();
            String xStoryContent = request.getParameter("storycontent").trim();
            sd.createNewStory(xStoryThumbnail, xStoryTitle, xStoryContent);
            List<Story> storyList = sd.getAllStory("all");
            request.setAttribute("storyList", storyList);
        }
        else{
            String xName = request.getParameter("xName").trim();
            List<Story> storyList= sd.getAllStory(xName);
            request.setAttribute("storyList", storyList);
        }

        request.getRequestDispatcher("storyListManager.jsp").forward(request, response);

    }
}
