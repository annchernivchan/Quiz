package servlets.question_servlets;

import db_services.QuestionService;
import entities.Answer;
import entities.Question;
import entities.QuestionType;
import parser.JsonParser;
import validation.QuestionValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DoAddQuestionServlet", urlPatterns = "/doAddQuestion")
public class DoAddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        Question newQuestion = new Question();

        newQuestion.setQuestionText(request.getParameter("questionText"));
        newQuestion.setPoint(Double.valueOf(request.getParameter("questionPoint")));
        newQuestion.setQuestionType(QuestionType.valueOf(request.getParameter("questionType")));

        String[] answerTexts = request.getParameterValues("fieldAnswerText");
        String[] answerWeights = request.getParameterValues("fieldAnswerWeight");

        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < answerTexts.length; i++) {
            Answer answer = new Answer(answerTexts[i], Double.valueOf(answerWeights[i]));
            answers.add(answer);
        }

        newQuestion.setAllAnswers(answers);

        questionService.add(newQuestion);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
