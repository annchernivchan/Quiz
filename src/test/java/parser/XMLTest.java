package parser;

import entities.Question;
import entities.QuestionType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLTest {

    @Test
    public void isXmlConvertationCorrect() {
        XmlParser xmlParser = new XmlParser();

        Question question = new Question("This is my question", 10, QuestionType.MULTI_CHOICE);

        String questionList = xmlParser.to(question);
        Question newQuestionObject = xmlParser.from(questionList, Question.class);
        Assert.assertEquals(question, newQuestionObject);
    }

}
