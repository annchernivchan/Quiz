package validation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class QuestionValidationTest {

    @Test
    public void isQuestionPointCorrect(){
        String point = "44.55";
        Assert.assertTrue(QuestionValidator.isQuestionPointCorrect(point));
    }

    @Test
    public void isQuestionTypeCorrect(){
        String type = "MULTI_CHOICE";
        Assert.assertTrue(QuestionValidator.isQuestionTypeCorrect(type));
    }

}
