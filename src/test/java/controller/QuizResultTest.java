package controller;

import entities.Answer;
import entities.Question;
import entities.QuestionType;
import entities.UserAnswer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.UUID;

public class QuizResultTest {

    @Test
    public void isCorrectTestResult() {
        TestProcess test = new TestProcess();
        Task task = new Task();

        task.addQuestion(new Question("Name the spring months", 12, QuestionType.MULTI_CHOICE));

        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("March", 35));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("April", 30));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("May", 35));
        task.addAnswerForQuestion(task.getQuestions().get(0), new Answer("December", 0));

        ArrayList<UUID> firstQuestionAnswer = new ArrayList<>();
        firstQuestionAnswer.add(task.getQuestions().get(0).getAllAnswers().get(0).getId());
//        firstQuestionAnswer.add(task.getQuestions().get(0).getAllAnswers().get(1).getId());
        firstQuestionAnswer.add(task.getQuestions().get(0).getAllAnswers().get(2).getId());

        task.addUserAnswer(new UserAnswer(task.getQuestions().get(0), firstQuestionAnswer));

    }

}
