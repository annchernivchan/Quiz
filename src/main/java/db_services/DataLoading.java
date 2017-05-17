package db_services;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import entities.Question;
import entities.Task;

import java.io.File;
import java.io.IOException;


public class DataLoading {

    public void loadQuestionsIntoDatabase(File file) {
        QuestionService questionService = new QuestionService();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Question[] questions = mapper.readValue(file, Question[].class);
            for (Question question : questions) {
                questionService.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadQuestionsFromDatabase(String fileName) {
        QuestionService questionService = new QuestionService();
        Question[] questions = questionService.getAll().toArray(new Question[0]);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(System.getProperty("user.dir") + "/src/main/resources/"+ fileName + ".json"), questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasksIntoDatabase(File file) {
        TaskService taskService = new TaskService();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            Task[] tasks = mapper.readValue(file, Task[].class);
            for (Task task : tasks) {
                taskService.createTask(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTasksFromDatabase(String fileName) {
        TaskService taskService = new TaskService();
        Task[] tasks = taskService.getAll().toArray(new Task[0]);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(System.getProperty("user.dir") + "/src/main/resources/"+ fileName + ".json"), tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
