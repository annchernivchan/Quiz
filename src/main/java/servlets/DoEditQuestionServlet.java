package servlets;

import db_services.QuestionService;
import entities.Question;
import entities.QuestionType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DoEditQuestionServlet", urlPatterns = "/doEditQuestion")
public class DoEditQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UUID questionId = UUID.fromString(request.getParameter("id"));
        Question question = new Question();
        question.setId(UUID.fromString(request.getParameter("id")));
        question.setQuestionText(request.getParameter("questionText"));
        question.setPoint(Double.valueOf(request.getParameter("questionPoint")));
        question.setQuestionType(QuestionType.valueOf(request.getParameter("questionType")));
        String errorString = "";
        QuestionService questionService = new QuestionService();
        questionService.update(question);
        request.setAttribute("question", question);
        request.setAttribute("errorString", errorString);
        response.sendRedirect(request.getContextPath() + "/questions");
    }
}