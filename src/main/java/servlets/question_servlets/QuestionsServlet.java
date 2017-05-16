package servlets.question_servlets;

import db_services.TaskService;
import entities.Question;
import db_services.QuestionService;
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


@WebServlet(name = "QuestionsServlet", urlPatterns = {"/questions"})
public class QuestionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        TaskService taskService = new TaskService();

        List<Task> allTasks = taskService.getAll();
        List<Question> questions = questionService.getAll();
        List<Boolean> isInTasks = new ArrayList<>();

        for (Question question: questions) {
            boolean isInTask = false;
            for (Task task : allTasks) {
                if (taskService.isQuestionInTask(task.getId(), question.getId())) {
                    isInTask = true;
                    break;
                }
            }
            isInTasks.add(isInTask);
        }

        request.setAttribute("questions", questions);
        request.setAttribute("isInTasks", isInTasks);
        request.getRequestDispatcher("jsp/questions/questionsList.jsp").forward(request, response);
    }
}
