package main;

import db_services.DBStructure;
import db_services.DataLoading;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        DBStructure dbStructure = new DBStructure();
//        dbStructure.createStructure();
        DataLoading dataLoading = new DataLoading();
        dataLoading.loadQuestionsIntoDatabase(new File(System.getProperty("user.dir") + "/src/main/resources/newQuestions.json"));
        dataLoading.loadTasksIntoDatabase(new File(System.getProperty("user.dir") + "/src/main/resources/newTasks.json"));
    }


}
