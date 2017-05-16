package servlets.question_servlets;

import entities.QuestionType;
import parser.JsonParser;
import validation.AnswerValidator;
import validation.QuestionValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ValidateQuestionServlet", urlPatterns = "/validateQuestion")
public class ValidateQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> errors = new ArrayList<>();

        if (!QuestionValidator.isQuestionTextCorrect(request.getParameter("questionText"))) {
            errors.add(" Question text should start with upper letter and be less than 255 characters!");
        }
        if (!QuestionValidator.isQuestionPointCorrect(request.getParameter("questionPoint"))) {
            errors.add(" Question point should be integer number or double, but separated with dot!");
        }

        String questionType = request.getParameter("questionType");
        if (!QuestionValidator.isQuestionTypeCorrect(questionType)) {
            errors.add(" Question type is incorrect!");
        }

        String[] answerTexts = request.getParameterValues("fieldAnswerText");
        String[] answerWeights = request.getParameterValues("fieldAnswerWeight");
        boolean isValidAnswerWeights = true;

        if (answerTexts == null || answerTexts.length == 0) {
            errors.add(" Question should have answers!");
        } else {
            for (int i = 0; i < answerTexts.length; i++) {
                if (!AnswerValidator.isAnswerTextCorrect(answerTexts[i])) {
                    errors.add(" Each answer should be less than 100 characters!");
                    break;
                }
                if (!AnswerValidator.isAnswerWeightCorrect(answerWeights[i])) {
                    errors.add(" Each answer should have weight from 0 to 100 percent!");
                    isValidAnswerWeights = false;
                    break;
                }
            }

            if (isValidAnswerWeights) {

                if (QuestionType.valueOf(questionType) == QuestionType.ONE_RIGHT_ANSWER) {
                    if (answerTexts.length < 2) {
                        errors.add(" Question should have at least two answers!");
                    } else {
                        if (!isOneRightAnswersWeightsValid(answerWeights)) {
                            errors.add(" If question has one right answer, this answer should have 100 percent and other should have 0 percent!");
                        }
                    }
                } else if (QuestionType.valueOf(questionType) == QuestionType.MULTI_CHOICE) {
                    if (answerTexts.length < 3) errors.add(" Question should have at least three answers!");
                    else {
                        int zeroWeightAnswers = 0;
                        int nonZeroWeightAnswers = 0;
                        for (String answerWeight : answerWeights) {
                            double weight = Double.valueOf(answerWeight);
                            if (weight == 0) zeroWeightAnswers++;
                            else nonZeroWeightAnswers++;
                        }
                        if (zeroWeightAnswers == 0) {
                            errors.add(" There should be at least one wrong answer in question!");
                        }
                        if (nonZeroWeightAnswers < 2) {
                            errors.add(" There must be at least two right answers in question!");
                        }
                    }
                }
            }
        }

        if (!errors.isEmpty()) {
            JsonParser jsonParser = new JsonParser();
            String jsonErrors = jsonParser.to(errors);
            response.setContentType("text/plain");
            response.getWriter().write(jsonErrors);
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("");
        }
    }


    private boolean isOneRightAnswersWeightsValid(String[] answersWeight) {
        boolean isValid = false;
        for (String answerWeight : answersWeight) {
            double weight = Double.valueOf(answerWeight);
            if (weight != 100 && weight != 0) return false;
            if (!isValid) {
                if (weight == 100) isValid = true;
            } else {
                if (weight == 100) return false;
            }
        }
        return isValid;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
