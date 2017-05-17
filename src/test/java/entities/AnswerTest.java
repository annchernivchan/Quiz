package entities;

import controller.Task;
import org.testng.Assert;
import org.testng.annotations.Test;
import validation.AnswerValidator;

import java.util.ArrayList;

public class AnswerTest {

    @Test
    public void isAnswerTextCorrect() {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("first123"));
        answers.add(new Answer("SECOND..."));
        answers.add(new Answer("third"));

        for (Answer answer : answers) {
            Assert.assertEquals(AnswerValidator.isAnswerTextCorrect(answer.getAnswerText()), true);
        }
    }


}
