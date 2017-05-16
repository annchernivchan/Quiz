package servlets.task_servlets;

import db_services.QuestionService;
import db_services.TaskService;
import entities.Question;
import entities.Task;
import validation.TaskValidator;

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

        String errorTaskName = null;
        String taskName = request.getParameter("taskName");
        Task task = new Task();
        if (TaskValidator.isTaskNameCorrect(taskName)) {
            task.setName(taskName);
        } else errorTaskName = " Task name should start with upper letter and be less than 50 characters!";

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
        request.setAttribute("errorString", errorTaskName);

        if (errorTaskName == null) {
            taskService.createTask(task);
        } else {
            response.setContentType("text/plain");
            response.getWriter().write(errorTaskName);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}