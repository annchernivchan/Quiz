package db_services;

import dao.QuestionDAO;
import db_connection.Connector;
import entities.Answer;
import entities.Question;
import entities.QuestionType;
import entities.Task;
import javafx.scene.image.Image;
import validation.AnswerValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The {@code QuestionService} class represents user interaction with questions table in database.
*/

public class QuestionService implements QuestionDAO {

    /** Method represents adding entity {@code Question} into the database
     * @return is operation success
     * */

    @Override
    public boolean add(Question question) {
        int questionTypeId = 0;

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String questionTypeSql = "SELECT id FROM question_type WHERE type = ?";
        String questionSql = "INSERT INTO questions(id, text, point, question_type_id, verified) VALUES (?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(questionTypeSql);
            preparedStatement.setString(1, question.getQuestionType().name());
            ResultSet questionTypeSet = preparedStatement.executeQuery();
            if (questionTypeSet.next()) questionTypeId = questionTypeSet.getInt("id");
            boolean isVerified = question.getAnswersWeight() == 100;

            preparedStatement = connection.prepareStatement(questionSql);
            preparedStatement.setString(1, question.getId().toString());
            preparedStatement.setString(2, question.getQuestionText());
            preparedStatement.setDouble(3, question.getPoint());
            preparedStatement.setInt(4, questionTypeId);
            preparedStatement.setBoolean(5, isVerified);

            AnswerService answerService = new AnswerService();
            answerService.addAll(question, question.getAllAnswers());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    /** Method represents removing entity {@code Question} from the database
     * @return is operation success
     * */

    @Override
    public boolean remove(UUID id) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlQuestion = "DELETE FROM questions WHERE id = ?";
        String sqlAnswers = "DELETE FROM answers WHERE question_id = ?";
        String sqlTasks = "DELETE FROM task_questions WHERE question_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuestion);
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(sqlAnswers);
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(sqlTasks);
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();
            TaskService taskService = new TaskService();
            taskService.recalculateTasksPoints();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    /** Method represents updating entity {@code Question} in the database
     * @return is operation success
     * */

    @Override
    public boolean update(Question question) {
        int questionTypeId = 0;

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        String questionTypeSql = "SELECT id FROM question_type WHERE type = ?";
        String sql = "UPDATE questions SET text = ?, point = ?, question_type_id = ?, verified = ? WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(questionTypeSql);
            preparedStatement.setString(1, question.getQuestionType().name());
            ResultSet questionTypeSet = preparedStatement.executeQuery();
            if (questionTypeSet.next()) questionTypeId = questionTypeSet.getInt("id");

            boolean isVerified = question.getAnswersWeight() == 100;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getQuestionText());
            preparedStatement.setDouble(2, question.getPoint());
            preparedStatement.setInt(3, questionTypeId);
            preparedStatement.setBoolean(4, isVerified);
            preparedStatement.setString(5, question.getId().toString());

            preparedStatement.execute();

            AnswerService answerService = new AnswerService();
            List<Answer> newAnswers = new ArrayList<>();
            List<Answer> oldAnswers = new ArrayList<>();

            for (Answer answer : question.getAllAnswers()) {
                if (answerService.getById(answer.getId()) == null) newAnswers.add(answer);
                else oldAnswers.add(answer);
            }

            answerService.addAll(question, newAnswers);
            answerService.updateAll(oldAnswers);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    /** Method represents getting list of entity {@code Question} from the database
     * @return list of entities
     * */

    @Override
    public List<Question> getAll() {
        AnswerService answerService = new AnswerService();
        List<Question> questions = new ArrayList<>();

        Connection connection = Connector.getConnection();
        Statement statement = null;
        String sql = "SELECT questions.id, text, point, question_type.type, verified FROM questions INNER JOIN question_type ON questions.question_type_id = question_type.id";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(UUID.fromString(resultSet.getString("id")));
                question.setQuestionText(resultSet.getString("text"));
                question.setPoint(resultSet.getDouble("point"));
                question.setQuestionType(QuestionType.valueOf(resultSet.getString("type")));
                question.setVerified(resultSet.getBoolean("verified"));
                question.setAllAnswers(answerService.getAllByQuestion(question));
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

    /** Method represents getting all verified entity {@code Question} from the database
     * @return list of entities
     * */

    @Override
    public List<Question> getAllVerified() {
        List<Question> verifiedQuestions = new ArrayList<>();
        for (Question question : getAll()) {
            if (question.isVerified()) verifiedQuestions.add(question);
        }
        return verifiedQuestions;
    }

    /** Method represents getting all available entity {@code Question} from the database
     * @return list of entities
     * */

    @Override
    public List<Question> getAvailableForTask(UUID taskId) {
        TaskService taskService = new TaskService();
        Task task = taskService.getTaskById(taskId);

        return getAvailableForTask(task);
    }

    public List<Question> getAvailableForTask(Task task) {
        List<Question> availableQuestions = new ArrayList<>();

        for (Question question : getAll()) {
            if (question.isVerified() && !task.getQuestions().contains(question)) {
                availableQuestions.add(question);
            }
        }
        return availableQuestions;
    }


    @Override
    public Question getById(UUID id) {
        Question question = null;

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT questions.id, text, point, question_type.type, verified FROM questions INNER JOIN question_type ON question_type.id = questions.question_type_id WHERE questions.id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                question = new Question();
                question.setId(UUID.fromString(resultSet.getString("id")));
                question.setQuestionText(resultSet.getString("text"));
                question.setPoint(resultSet.getDouble("point"));
                question.setQuestionType(QuestionType.valueOf(resultSet.getString("type")));
                question.setVerified(resultSet.getBoolean("verified"));
                question.setAllAnswers(getAnswersForQuestion(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return question;
    }

    @Override
    public List<Answer> getAnswersForQuestion(UUID id) {
        List<Answer> answers = new ArrayList<>();

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, text, weight FROM answers WHERE question_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(UUID.fromString(resultSet.getString("id")));
                answer.setAnswerText(resultSet.getString("text"));
                answer.setWeight(resultSet.getDouble("weight"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return answers;
    }

    @Override
    public boolean isVerified(UUID id) {
        QuestionService questionService = new QuestionService();
        Question question = questionService.getById(id);
        return question.getAnswersWeight() == 100;

    }

    @Override
    public boolean verifyQuestion(Question question) {

        boolean isVerified = question.getAnswersWeight() == 100;
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE questions SET verified = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, isVerified);
            preparedStatement.setString(2, question.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }

        return true;
    }

    public void addAnswer(Question question, Answer answer) {
        if (AnswerValidator.canAddAnswer(question, answer)) {
            question.getAllAnswers().add(answer);
        }
    }

    public List<Answer> getRightAnswers(List<Answer> answers) {
        List<Answer> rightAnswers = new ArrayList<>();
        for (Answer a : answers) {
            if (a.isRight()) rightAnswers.add(a);
        }
        return rightAnswers;
    }

    public double getAnswersWeight(Question question) {
        double weight = 0;
        for (Answer answer : question.getAllAnswers()) weight += answer.getWeight();
        return weight;
    }

    @Override
    public List<String> getAllTypes() {
        List<String> types = new ArrayList<>();

        Connection connection = Connector.getConnection();
        Statement statement = null;
        String sql = "SELECT type FROM question_type";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                types.add(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.close(connection, statement);
        }
        return types;
    }
}
