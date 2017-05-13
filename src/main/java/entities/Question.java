package entities;

import db_services.QuestionService;
import validation.AnswerValidator;
import validation.QuestionValidator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Question {

    private UUID id = UUID.randomUUID();
    private double point;
    private String questionText;
    private List<Answer> allAnswers;
    private QuestionType questionType;
    private boolean verified;

    public Question() {
        this.allAnswers = new ArrayList<>();
    }

    public Question(String questionText, double point, QuestionType questionType) {
        if (QuestionValidator.isQuestionTextCorrect(questionText)) this.questionText = questionText;
        this.point = point;
        this.allAnswers = new ArrayList<>();
        this.questionType = questionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(List<Answer> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public void addAnswer(Answer answer) {
        QuestionService questionService = new QuestionService();
        questionService.addAnswer(this, answer);
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public double getPoint() {
        return this.point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Answer> getRightAnswers() {
        QuestionService questionService = new QuestionService();
        return questionService.getRightAnswers(this.getAllAnswers());
    }

    public List<Answer> getRightAnswers(List<Answer> answers) {
        QuestionService questionService = new QuestionService();
        return questionService.getRightAnswers(answers);
    }

    public double getAnswersWeight() {
        QuestionService questionService = new QuestionService();
        return questionService.getAnswersWeight(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        return questionText != null ? questionText.equals(question.questionText) : question.questionText == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", point=" + point +
                ", questionText='" + questionText + '\'' +
                ", allAnswers=" + allAnswers +
                ", questionType=" + questionType +
                '}';
    }
}
