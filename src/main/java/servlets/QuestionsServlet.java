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


@WebServlet(name = "QuestionsServlet", urlPatterns = {"/questions"})
public class QuestionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.getAll();
        request.setAttribute("questions", questions);
        request.getRequestDispatcher("jsp/questions.jsp").forward(request, response);
    }
}
