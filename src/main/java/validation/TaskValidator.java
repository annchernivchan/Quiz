package validation;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskValidator {

    public static boolean isTaskNameCorrect(String taskName){
        Pattern pattern = Pattern.compile("\\p{Upper}.{0,49}");
        Matcher matcher = pattern.matcher(taskName);
        return matcher.matches();
    }

}
