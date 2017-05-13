package servlets.task_servlets;

import db_services.QuestionService;
import db_services.TaskService;
import entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "EditTaskServlet", urlPatterns = "/editTask")
public class EditTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = request.getParameter("id");
        TaskService taskService = new TaskService();
        QuestionService questionService = new QuestionService();
        Task taskById = taskService.getTaskById(UUID.fromString(taskId));
        request.setAttribute("task", taskById);
        request.setAttribute("availableQuestions", questionService.getAvailableForTask(UUID.fromString(taskId)));
        request.getRequestDispatcher("jsp/tasks/editTask.jsp").forward(request, response);
    }
}