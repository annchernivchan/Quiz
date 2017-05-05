package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionValidator {

    public static boolean isQuestionTextCorrect(String questionText) {
        Pattern p = Pattern.compile("\\p{Upper}.{0,254}");
        Matcher m = p.matcher(questionText);
        return m.matches();
    }
}
