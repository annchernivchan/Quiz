package main;

import db_services.DataLoading;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataLoading dataLoading = new DataLoading();
        dataLoading.loadQuestionsIntoDatabase(new File(System.getProperty("user.dir") + "/src/main/resources/newQuestions.json"));
        dataLoading.loadTasksIntoDatabase(new File(System.getProperty("user.dir") + "/src/main/resources/newTasks.json"));
    }


}
