package db_services;

import dao.TaskDAO;
import db_connection.Connector;
import entities.Question;
import entities.QuestionType;
import entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskService implements TaskDAO {

    @Override
    public void createTask(Task task) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO tasks (id, name, total_point) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, task.getId().toString());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setDouble(3, task.getTotalPoint());
            preparedStatement.execute();

            sql = "INSERT INTO task_questions (task_id, question_id) VALUES (?, ?)";
            for (Question question : task.getQuestions()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, task.getId().toString());
                preparedStatement.setString(2, question.getId().toString());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public Task getTaskById(UUID taskId) {
        Task task = new Task();
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT name, total_point FROM tasks WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            ResultSet taskResultSet = preparedStatement.executeQuery();
            if (taskResultSet.next()) {
                task.setId(taskId);
                task.setName(taskResultSet.getString("name"));
                task.setTotalPoint(taskResultSet.getDouble("total_point"));
            }

            sql = "SELECT questions.id, text, point, type  FROM questions " +
                    "INNER JOIN question_type ON question_type_id = question_type.id " +
                    "INNER JOIN task_questions ON task_questions.question_id = questions.id " +
                    "WHERE task_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            ResultSet questionsResultSet = preparedStatement.executeQuery();

            QuestionService questionService = new QuestionService();
            List<Question> questions = new ArrayList<>();

            while (questionsResultSet.next()) {
                Question question = new Question();
                question.setId(UUID.fromString(questionsResultSet.getString("id")));
                question.setQuestionText(questionsResultSet.getString("text"));
                question.setPoint(questionsResultSet.getDouble("point"));
                question.setQuestionType(QuestionType.valueOf(questionsResultSet.getString("type")));
                question.setAllAnswers(questionService.getAnswersForQuestion(question.getId()));

                questions.add(question);
            }

            task.setQuestions(questions);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return task;
    }

    @Override
    public void deleteTask(UUID taskId) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sqlTask = "DELETE FROM tasks WHERE id = ?";
            String sql = "DELETE FROM task_questions WHERE task_id = ?";
            preparedStatement = connection.prepareStatement(sqlTask);
            preparedStatement.setString(1, taskId.toString());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public void update(Task task) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        Task oldTask = getTaskById(task.getId());

        try {
            String sql = "UPDATE tasks SET name = ?, total_point = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setDouble(2, task.getTotalPoint());
            preparedStatement.setString(3, task.getId().toString());
            preparedStatement.execute();

            String sqlInsert = "INSERT INTO task_questions (task_id, question_id) VALUES (?,?)";
            String sqlDelete = "DELETE FROM task_questions WHERE task_id = ? AND question_id = ?";

            for (Question oldQuestion : oldTask.getQuestions()) {
                if (!task.getQuestions().contains(oldQuestion)) {
                    preparedStatement = connection.prepareStatement(sqlDelete);
                    preparedStatement.setString(1, task.getId().toString());
                    preparedStatement.setString(2, oldQuestion.getId().toString());
                    preparedStatement.execute();
                }
            }

            for (Question question : task.getQuestions()) {
                if (!isQuestionInTask(task.getId(), question.getId())) {
                    preparedStatement = connection.prepareStatement(sqlInsert);
                    preparedStatement.setString(1, task.getId().toString());
                    preparedStatement.setString(2, question.getId().toString());
                    preparedStatement.execute();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public void addQuestion(UUID taskId, Question question) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO task_questions (task_id, question_id) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
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
    public void removeQuestion(UUID taskId, UUID questionId) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM task_questions WHERE task_id = ? AND question_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            preparedStatement.setString(2, questionId.toString());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public List<Question> getQuestions(UUID taskId) {
        List<Question> questions = new ArrayList<>();

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT questions.id, questions.text, questions.point FROM task_questions INNER JOIN questions ON questions.id = task_questions.question_id AND tasks.id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
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
            Connector.close(connection, preparedStatement);
        }
        return questions;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        Connection connection = Connector.getConnection();
        Statement statement = null;

        try {
            String sql = "SELECT id FROM tasks";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = getTaskById(UUID.fromString(resultSet.getString("id")));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, statement);
        }
        return tasks;
    }

    @Override
    public boolean isQuestionInTask(UUID taskId, UUID questionId) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "SELECT question_id FROM task_questions WHERE task_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, taskId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (questionId.toString().equals(resultSet.getString("question_id"))) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return false;
    }

}
