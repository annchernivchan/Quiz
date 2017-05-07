package servlets;

import db_services.QuestionService;
import entities.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EditQuestionServlet", urlPatterns = "/editQuestion")
public class EditQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID questionId = UUID.fromString(request.getParameter("id"));
        QuestionService questionService = new QuestionService();
        Question question = questionService.getById(questionId);
        request.setAttribute("types", questionService.getAllTypes());
        request.setAttribute("question", question);
        request.getRequestDispatcher("jsp/editQuestion.jsp").forward(request, response);
    }
}
