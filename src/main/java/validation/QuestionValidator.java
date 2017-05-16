package validation;

import entities.QuestionType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionValidator {

    public static boolean isQuestionTextCorrect(String questionText) {
        Pattern p = Pattern.compile("\\p{Upper}.{0,254}");
        Matcher m = p.matcher(questionText);
        return m.matches();
    }

    public static boolean isQuestionPointCorrect(String questionPoint) {
        Pattern p = Pattern.compile("\\d{1,5}((\\.)\\d{0,2})?");
        Matcher m = p.matcher(questionPoint);
        return m.matches();
    }

    public static boolean isQuestionTypeCorrect(String questionType) {
        for (QuestionType type : QuestionType.values()) {
            if (QuestionType.valueOf(questionType) == type) return true;
        }
        return false;
    }
}
