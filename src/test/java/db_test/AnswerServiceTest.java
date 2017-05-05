package db_test;

import entities.Answer;
import entities.Question;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.db_services.AnswerService;
import services.db_services.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static entities.QuestionType.MULTI_CHOICE;

public class AnswerServiceTest {

    @Test
    public void insertAnswersTest() {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();

        Question question = new Question("Choose winter months", 15, MULTI_CHOICE);
        question.setId(UUID.fromString("7c95885e-2d5f-4970-8b53-244f89ac4cae"));

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("January", 70));
        answers.add(new Answer("February", 20));
        answers.add(new Answer("December", 10));

        int before = questionService.getAnswersForQuestion(question).size();
        answerService.addAll(question, answers);
        int after = questionService.getAnswersForQuestion(question).size();
        if (before + answers.size() == after) answerService.removeAll(answers);
        Assert.assertEquals(before + answers.size(), after);
    }

    @Test
    public void insertAnswerTest() {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();

        Question question = new Question("Choose winter months", 15, MULTI_CHOICE);
        question.setId(UUID.fromString("7c95885e-2d5f-4970-8b53-244f89ac4cae"));

        Answer answer = new Answer("SUMMER", 10);

        int before = questionService.getAnswersForQuestion(question).size();
        answerService.add(question, answer);
        int after = questionService.getAnswersForQuestion(question).size();
        if (before + 1 == after) answerService.remove(answer);
        Assert.assertEquals(before + 1, after);
    }

    @Test
    public void removeAnswerTest() {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();

        Question question = new Question("Choose winter months", 15, MULTI_CHOICE);
        question.setId(UUID.fromString("7c95885e-2d5f-4970-8b53-244f89ac4cae"));

        Answer answer = new Answer("WINTER", 16.2);
        answer.setId(UUID.fromString("ca03e4c2-d665-4999-bb88-31135048900b"));

        int before = questionService.getAnswersForQuestion(question).size();
        answerService.remove(answer);
        int after = questionService.getAnswersForQuestion(question).size();
        answerService.add(question, answer);
        Assert.assertEquals(before, after + 1);
    }


    @Test
    public void removeAnswersTest() {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.getAll();
        boolean isSuccess = questionService.remove(questions.get(questions.size() - 1));
        questionService.add(questions.get(questions.size() - 1));
        Assert.assertEquals(isSuccess, true);
    }

//    @Test
//    public void updateQuestionTest() {
//        QuestionService questionService = new QuestionService();
//        List<Question> questions = questionService.getAll();
//        Question oldQuestion = questions.get(0);
//        Question newQuestion = new Question(oldQuestion.getQuestionText(), oldQuestion.getPoint(), oldQuestion.getQuestionType());
//        newQuestion.setQuestionText("Hello?");
//        boolean isSuccess = questionService.update(newQuestion);
//        questionService.update(oldQuestion);
//        Assert.assertEquals(isSuccess, true);
//    }
//
//    @Test
//    public void getAllQuestionsTest() {
//        QuestionService questionService = new QuestionService();
//        List<Question> questions = questionService.getAll();
//        Assert.assertNotEquals(questions, null);
//    }
//
//    @Test
//    public void getQuestionByIdTest() {
//        QuestionService questionService = new QuestionService();
//        List<Question> questions = questionService.getAll();
//        Question question = questionService.getById(questions.get(0).getId());
//        Assert.assertNotEquals(question, null);
//    }
}
