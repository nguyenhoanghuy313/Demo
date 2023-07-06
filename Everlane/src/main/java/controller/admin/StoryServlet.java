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

        List<Story> storyList= sd.getAllStory("all");
        request.setAttribute("storyList", storyList);

        request.getRequestDispatcher("storyListManager.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoryDAO sd = new StoryDAO();

        String xName = request.getParameter("xName").trim();

        List<Story> storyList= sd.getAllStory(xName);
        request.setAttribute("storyList", storyList);

        request.getRequestDispatcher("storyListManager.jsp").forward(request, response);

    }
}
