package dao;

import entities.Question;
import entities.Task;

import java.util.List;

public interface TaskDAO {

    void createTask(String name);
    void deleteTask(String name);

    void addQuestion(Task task, Question question);
    void removeQuestion(Task task, Question question);
    List<Question> getQuestions(Task task);
}
