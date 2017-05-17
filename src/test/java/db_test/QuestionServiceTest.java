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

        Question question = questionService.getById(UUID.fromString("b2b4ae3e-fbd1-4db4-b399-1b7560efbb1e"));

        int oldQuestionSize = questionService.getAll().size();
        questionService.remove(question.getId());
        int newQuestionSize = questionService.getAll().size();
        if (oldQuestionSize - 1 == newQuestionSize) questionService.add(question);
        Assert.assertEquals(newQuestionSize, oldQuestionSize - 1);
    }

    @Test
    public void updateQuestionTest() {
        QuestionService questionService = new QuestionService();

        Question oldQuestion = questionService.getById(UUID.fromString("b2b4ae3e-fbd1-4db4-b399-1b7560efbb1e"));

        Question newQuestion = new Question();
        newQuestion.setId(UUID.fromString("b2b4ae3e-fbd1-4db4-b399-1b7560efbb1e"));
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
        Question question = questionService.getById(UUID.fromString("b2b4ae3e-fbd1-4db4-b399-1b7560efbb1e"));
        Assert.assertNotNull(question);
    }

    @Test
    public void getAnswersForQuestionTest() {
        QuestionService questionService = new QuestionService();

        Question question = questionService.getById(UUID.fromString("b2b4ae3e-fbd1-4db4-b399-1b7560efbb1e"));

        List<Answer> answers = questionService.getAnswersForQuestion(question.getId());
        Assert.assertNotNull(answers);
    }


}
