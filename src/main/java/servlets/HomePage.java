package servlets;

import entities.Question;
import db_services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "HomePage", urlPatterns = {"/home", "/"})
public class HomePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.getAll();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
