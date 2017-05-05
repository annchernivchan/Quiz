package main;

import entities.Question;
import db_services.QuestionService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuestionService questionService = new QuestionService();
        List<Question> questions = questionService.getAll();
        System.out.println(questions.size());
    }


}
