package servlets.question_servlets;

import db_services.AnswerService;
import db_services.QuestionService;
import entities.Answer;
import entities.Question;
import entities.QuestionType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DoEditQuestionServlet", urlPatterns = "/doEditQuestion")
public class DoEditQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID questionId = UUID.fromString(request.getParameter("id"));

        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        Question question = questionService.getById(questionId);

        question.setQuestionText(request.getParameter("questionText"));
        question.setPoint(Double.valueOf(request.getParameter("questionPoint")));
        question.setQuestionType(QuestionType.valueOf(request.getParameter("questionType")));

//        List<Answer> answers = new ArrayList<>();
//
//        String[] answersId = request.getParameterValues("answerId");
//        String[] answerTexts = request.getParameterValues("fieldAnswerText");
//        String[] answerWeights = request.getParameterValues("fieldAnswerWeight");
//
//        for (int i = 0; i < answerTexts.length; i++) {
//            Answer answer = new Answer(answerTexts[i], Double.valueOf(answerWeights[i]));
//            if (!answerService.isDefault(answersId[i])) answer.setId(UUID.fromString(answersId[i]));
//            answers.add(answer);
//        }
//
//        question.setAllAnswers(answers);

        questionService.update(question);

        request.setAttribute("question", question);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}