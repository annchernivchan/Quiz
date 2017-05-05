package db_services;

import dao.TaskDAO;
import db_connection.Connector;
import entities.Question;
import entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService implements TaskDAO {

    @Override
    public void createTask(String name) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO tasks (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public void deleteTask(String name) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM tasks WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public void addQuestion(Task task, Question question) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO task_questions (task_id, question_id) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, task.getId());
            preparedStatement.setString(2, question.getId().toString());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public void removeQuestion(Task task, Question question) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM task_questions WHERE task_id = ? AND question_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, task.getId());
            preparedStatement.setString(2, question.getId().toString());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public List<Question> getQuestions(Task task) {
        List<Question> questions = new ArrayList<>();

        Connection connection = Connector.getConnection();
        Statement statement = null;
        try {
            String sql = "SELECT questions.id FROM task_questions INNER JOIN question ON questions.id = task_questions.question_id";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(UUID.fromString(resultSet.getString("id")));
                question.setQuestionText(resultSet.getString("text"));
                question.setPoint(resultSet.getDouble("point"));

                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.close(connection, statement);
        }
        return questions;
    }
}
