package validation;

import entities.Answer;
import entities.Question;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerValidator {


    public static boolean isAnswerTextCorrect(Answer answer) {
        Pattern p = Pattern.compile(".{0,100}");
        Matcher m = p.matcher(answer.getAnswerText());
        return m.matches();
    }

    public static boolean isAnswersWeightCorrect(List<Answer> answers) {
        double answersWeight = 0;
        for (Answer answer : answers) answersWeight += answer.getWeight();
        return answersWeight == 100;
    }

    public static boolean canAddAnswer(Question question, Answer answer) {
//        return isAnswerTextCorrect(answer) && !(answer.isRight() && question.getQuestionType() == QuestionType.ONE_RIGHT_ANSWER && question.getRightAnswers().size() > 0);
        return true;
    }

}
