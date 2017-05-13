package servlets.task_servlets;

import db_services.QuestionService;
import db_services.TaskService;
import entities.Question;
import entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DoAddTaskServlet", urlPatterns = "/doAddTask")
public class DoAddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        TaskService taskService = new TaskService();

        String taskName = request.getParameter("taskName");
        Task task = new Task();
        task.setName(taskName);
        List<Question> questions = new ArrayList<>();
        String[] questionsInTask = request.getParameterValues("isInTask");
        double points = 0;
        for (String questionId : questionsInTask) {
            Question question = questionService.getById(UUID.fromString(questionId));
            points += question.getPoint();
            questions.add(question);
        }
        task.setTotalPoint(points);
        task.setQuestions(questions);
        taskService.createTask(task);

        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}