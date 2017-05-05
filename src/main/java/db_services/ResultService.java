package db_services;

import controller.TestProcess;
import dao.ResultDAO;
import db_connection.Connector;
import entities.Task;
import entities.User;
import entities.UserAnswer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ResultService implements ResultDAO {

    @Override
    public double saveResult(User user, Task task, List<UserAnswer> userAnswers) {
        TestProcess testProcess = new TestProcess();
        double taskResult = testProcess.getTaskResult(userAnswers);

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO results (user_id, task_id, score) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setLong(2, task.getId());
            preparedStatement.setDouble(3, taskResult);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return 0;
    }
}
