package dao;

import entities.Question;
import entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDAO {

    void createTask(Task task);
    Task getTaskById(UUID taskId);
    void deleteTask(UUID taskId);
    void update(Task task);
    void addQuestion(UUID taskId, Question question);
    void removeQuestion(UUID taskId, UUID questionId);
    List<Question> getQuestions(UUID taskId);
    List<Task> getAll();
    boolean isQuestionInTask(UUID taskId, UUID questionId);
    void recalculateTasksPoints();
}
