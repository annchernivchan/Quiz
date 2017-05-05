package controller;

import entities.Answer;
import entities.UserAnswer;

import java.util.List;
import java.util.UUID;

public class TestProcess {

    public double getTaskResult(List<UserAnswer> userAnswers) {
        double result = 0;
        for (UserAnswer userAnswer : userAnswers) {
            double userAnswerWeight = 0;
            List<Answer> rightAnswers = userAnswer.getQuestion().getRightAnswers();
            for (Answer rightAnswer : rightAnswers) {
                for (UUID answerId : userAnswer.getAnswersId())
                    if (rightAnswer.getId().equals(answerId)) userAnswerWeight += rightAnswer.getWeight();
        }
            result += (userAnswer.getQuestion().getPoint() * userAnswerWeight) / 100;
        }
        return result;
    }

}
