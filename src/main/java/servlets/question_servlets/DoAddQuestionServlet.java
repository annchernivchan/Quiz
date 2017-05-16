package servlets.question_servlets;

import db_services.QuestionService;
import entities.Answer;
import entities.Question;
import entities.QuestionType;
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
//        String questionTextError = null;
//        String questionPointError = null;

        Question newQuestion = new Question();

        String point = request.getParameter("questionPoint");

        String questionText = request.getParameter("questionText");
//        if (QuestionValidator.isQuestionTextCorrect(questionText))
            newQuestion.setQuestionText(questionText);
//        else {
//            questionTextError = "Question text is incorrect";
//            System.out.println("Question text is incorrect");
//        }
//        if (QuestionValidator.isQuestionPointCorrect(point))
            newQuestion.setPoint(Double.valueOf(point));
//        else {
//            questionPointError = "Question point is incorrect";
//            System.out.println("Question point is incorrect");
//        }
        newQuestion.setQuestionType(QuestionType.valueOf(request.getParameter("questionType")));

        List<Answer> answers = new ArrayList<>();
        String[] answerTexts = request.getParameterValues("fieldAnswerText");
        String[] answerWeights = request.getParameterValues("fieldAnswerWeight");

        for (int i = 0; i < answerTexts.length; i++) {
            Answer answer = new Answer(answerTexts[i], Double.valueOf(answerWeights[i]));
            answers.add(answer);
        }

        newQuestion.setAllAnswers(answers);

        request.setAttribute("question", newQuestion);
//        request.setAttribute("questionTextError", questionTextError);
//        request.setAttribute("questionPointError", questionPointError);

//        if (questionTextError != null || questionPointError != null) {
//            request.getRequestDispatcher("jsp/questions/addQuestion.jsp");
//        } else {
            questionService.add(newQuestion);
            response.sendRedirect(request.getContextPath() + "/questions");
//        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
