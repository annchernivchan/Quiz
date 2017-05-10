package servlets;

import db_services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddQuestionServlet", urlPatterns = "/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        List<String> types = questionService.getAllTypes();
        request.setAttribute("types", types);
        request.getRequestDispatcher("jsp/addQuestion.jsp").forward(request, response);
    }
}
