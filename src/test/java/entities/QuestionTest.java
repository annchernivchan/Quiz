package entities;

import org.testng.Assert;
import org.testng.annotations.Test;
import validation.QuestionValidator;

public class QuestionTest {

    @Test
    public void isQuestionTextCorrect() {
        Question question = new Question("Hello, it's question!", 10, QuestionType.MULTI_CHOICE);
        Assert.assertEquals(QuestionValidator.isQuestionTextCorrect(question.getQuestionText()), true);
    }

}
