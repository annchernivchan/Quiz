package servlets.task_servlets;

import db_services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DeleteTaskServlet", urlPatterns = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskId = request.getParameter("id");
        TaskService taskService = new TaskService();
        taskService.deleteTask(UUID.fromString(taskId));
        response.sendRedirect(request.getContextPath() + "/tasks");
    }
}