package parser;

import entities.Question;
import entities.QuestionType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSONTest {

    @Test
    public void isJSONConvertationCorrect() {

        JsonParser jsonParser = new JsonParser();

        Question question = new Question("This is my question", 10, QuestionType.MULTI_CHOICE);
        String questionList = jsonParser.to(question);

        Question newQuestionObject = jsonParser.from(questionList, Question.class);
        Assert.assertEquals(question, newQuestionObject);
    }

}
