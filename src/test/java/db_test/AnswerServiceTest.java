package db_test;

import entities.Answer;
import entities.Question;
import javafx.scene.image.Image;
import org.testng.Assert;
import org.testng.annotations.Test;
import db_services.AnswerService;
import db_services.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static entities.QuestionType.MULTI_CHOICE;

public class AnswerServiceTest {

    @Test
    public void insertAnswersTest() {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();

        Question question = questionService.getById(UUID.fromString("5b66c2c3-9b89-4f3a-aa62-ed04c0a6e3ed"));

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("one", 100));
        answers.add(new Answer("two", 0));
        answers.add(new Answer("three", 0));

        int before = questionService.getAnswersForQuestion(question.getId()).size();
        answerService.addAll(question, answers);
        int after = questionService.getAnswersForQuestion(question.getId()).size();
        if (before + answers.size() == after) answerService.removeAll(answers);
        Assert.assertEquals(before + answers.size(), after);
    }
}
