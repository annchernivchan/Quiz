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

@WebServlet(name = "DoEditTaskServlet", urlPatterns = "/doEditTask")
public class DoEditTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        TaskService taskService = new TaskService();
        QuestionService questionService = new QuestionService();
        Task task = taskService.getTaskById(UUID.fromString(id));
        task.setName(request.getParameter("taskName"));

        List<Question> questions = new ArrayList<>();
        String[] questionsInTask = request.getParameterValues("isInTask");
        double points = 0;
        for (String aQuestionsInTask : questionsInTask) {
            Question question = questionService.getById(UUID.fromString(aQuestionsInTask));
            points += question.getPoint();
            questions.add(question);
        }
        task.setTotalPoint(points);
        task.setQuestions(questions);
        taskService.update(task);
        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}