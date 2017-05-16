package validation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class QuestionValidationTest {

    @Test
    public void isQuestionPointCorrect(){
        String point = "4";
        Assert.assertTrue(QuestionValidator.isQuestionPointCorrect(point));
    }

}
