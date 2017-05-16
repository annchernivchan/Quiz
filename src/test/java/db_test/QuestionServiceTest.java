package db_test;

import entities.Answer;
import entities.Question;
import entities.QuestionType;
import org.testng.Assert;
import org.testng.annotations.Test;
import db_services.QuestionService;

import java.util.List;
import java.util.UUID;

public class QuestionServiceTest {

    @Test
    public void insertQuestionTest() {
        QuestionService questionService = new QuestionService();
        Question question = new Question("Choose winter months", 15, QuestionType.MULTI_CHOICE);
        int oldQuestionSize = questionService.getAll().size();
        questionService.add(question);
        int newQuestionSize = questionService.getAll().size();
        if (oldQuestionSize + 1 == newQuestionSize) questionService.remove(question.getId());
        Assert.assertEquals(newQuestionSize, oldQuestionSize + 1);
    }

    @Test
    public void removeQuestionTest() {
        QuestionService questionService = new QuestionService();

        Question question = new Question();
        question.setId(UUID.fromString("dbabd6d0-4e37-4abc-9892-7449ac428201"));
        question.setQuestionText("What is the capital of Great Britain?");
        question.setPoint(10);
        question.setQuestionType(QuestionType.ONE_RIGHT_ANSWER);

        int oldQuestionSize = questionService.getAll().size();
        questionService.remove(question.getId());
        int newQuestionSize = questionService.getAll().size();
        if (oldQuestionSize - 1 == newQuestionSize) questionService.add(question);
        Assert.assertEquals(newQuestionSize, oldQuestionSize - 1);
    }

    @Test
    public void updateQuestionTest() {
        QuestionService questionService = new QuestionService();

        Question oldQuestion = new Question();
        oldQuestion.setId(UUID.fromString("dbabd6d0-4e37-4abc-9892-7449ac428201"));
        oldQuestion.setQuestionText("What is the capital of Great Britain?");
        oldQuestion.setPoint(10);
        oldQuestion.setQuestionType(QuestionType.ONE_RIGHT_ANSWER);

        Question newQuestion = new Question();
        newQuestion.setId(UUID.fromString("dbabd6d0-4e37-4abc-9892-7449ac428201"));
        newQuestion.setQuestionText("Hello, it's new question");
        newQuestion.setPoint(10);
        newQuestion.setQuestionType(QuestionType.MULTI_CHOICE);

        questionService.update(newQuestion);

        boolean isSuccess = questionService.getById(newQuestion.getId()).getQuestionText().equals("Hello, it's new question");
        if (isSuccess) questionService.update(oldQuestion);
        Assert.assertTrue(isSuccess);
    }

    @Test
    public void getAllQuestionsTest() {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.getAll();
        Assert.assertNotNull(questions);
    }

    @Test
    public void getQuestionByIdTest() {
        QuestionService questionService = new QuestionService();
        Question question = questionService.getById(UUID.fromString("dbabd6d0-4e37-4abc-9892-7449ac428201"));
        Assert.assertNotNull(question);
    }

    @Test
    public void getAnswersForQuestionTest() {
        QuestionService questionService = new QuestionService();

        Question question = new Question();
        question.setId(UUID.fromString("dbabd6d0-4e37-4abc-9892-7449ac428201"));
        question.setQuestionText("What is the capital of Great Britain?");
        question.setPoint(10);
        question.setQuestionType(QuestionType.ONE_RIGHT_ANSWER);

        List<Answer> answers = questionService.getAnswersForQuestion(question.getId());
        Assert.assertNotNull(answers);
    }


}
