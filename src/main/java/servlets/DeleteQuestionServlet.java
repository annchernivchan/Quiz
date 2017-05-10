package servlets;

import db_services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DeleteQuestionServlet", urlPatterns = "/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        questionService.remove(UUID.fromString(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/questions");
    }
}
