package validation;

import entities.Answer;
import entities.Question;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerValidator {

    public static boolean isAnswerTextCorrect(String answer) {
        Pattern p = Pattern.compile(".{0,100}");
        Matcher m = p.matcher(answer);
        return m.matches();
    }

    public static boolean isAnswerWeightCorrect(String weight) {
        Pattern p = Pattern.compile("\\d{1,3}((\\.)\\d{0,2})?");
        Matcher m = p.matcher(weight);
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
