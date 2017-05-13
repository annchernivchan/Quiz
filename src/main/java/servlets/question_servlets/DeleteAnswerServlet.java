package servlets.question_servlets;

import db_services.AnswerService;
import db_services.QuestionService;
import entities.Answer;
import entities.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DeleteAnswerServlet", urlPatterns = "/deleteAnswer")
public class DeleteAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        answerService.remove(UUID.fromString(request.getParameter("answerId")));

        String questionId = request.getParameter("questionId");
        String answerId = request.getParameter("answerId");

        Question question = questionService.getById(UUID.fromString(questionId));
        question.getAllAnswers().remove(answerService.getById(UUID.fromString(answerId)));
        response.sendRedirect(request.getContextPath() + "/editQuestion?id=" + questionId);
    }
}
