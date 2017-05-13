package servlets.task_servlets;

import db_services.TaskService;
import entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskServlet", urlPatterns = "/tasks")
public class TaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskService taskService = new TaskService();
        List<Task> tasks = taskService.getAll();
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("jsp/tasks/tasksList.jsp").forward(request, response);
    }
}