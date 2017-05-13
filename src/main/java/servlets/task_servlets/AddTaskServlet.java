package servlets.task_servlets;

import db_services.QuestionService;
import entities.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddTaskServlet", urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        List<Question> verifiedQuestions = questionService.getAllVerified();
        request.setAttribute("questions", verifiedQuestions);
        request.getRequestDispatcher("jsp/tasks/addTask.jsp").forward(request, response);
    }
}