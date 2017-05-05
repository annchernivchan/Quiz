package dao;

import entities.Task;
import entities.User;
import entities.UserAnswer;

import java.util.List;

public interface ResultDAO {

    double saveResult(User user, Task task, List<UserAnswer> userAnswers);
}
