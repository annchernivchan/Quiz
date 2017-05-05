package controller;

import entities.Answer;
import entities.Question;
import entities.UserAnswer;

import java.util.ArrayList;

public class Task {

    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<UserAnswer> userAnswers = new ArrayList<>();

    public Task() {
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addAnswerForQuestion(Question question, Answer answer) {
        int index = questions.indexOf(question);
        questions.get(index).addAnswer(answer);
    }

    public void addAnswersForQuestion(Question question, ArrayList<Answer> answers) {
        int index = questions.indexOf(question);
        questions.get(index).setAllAnswers(answers);
    }

    public void addUserAnswer(UserAnswer answer) {
        if (userAnswers.size() < answer.getQuestion().getRightAnswers().size()) userAnswers.add(answer);
    }


}
