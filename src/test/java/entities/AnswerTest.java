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
            Assert.assertEquals(AnswerValidator.isAnswerTextCorrect(answer), true);
        }
    }

    @Test
    public void isAnswerWeightRecalculationCorrect () {
        Task task = new Task();

        task.addQuestion(new Question("Name the spring months", 12, QuestionType.MULTI_CHOICE));

        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("March", 70));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("April", 30));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("May", 21));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("December", 0));

        Assert.assertEquals(AnswerValidator.isAnswersWeightCorrect(task.getQuestions().get(0).getAllAnswers()), true);

    }

}
