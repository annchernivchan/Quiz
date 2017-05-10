package db_services;

import dao.AnswerDAO;
import db_connection.Connector;
import entities.Answer;
import entities.Question;
import validation.AnswerValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnswerService implements AnswerDAO {

    @Override
    public boolean add(Question question, Answer answer) {
        if (AnswerValidator.canAddAnswer(question, answer)) {
            Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = null;
            try {
                String sql = "INSERT INTO answers (question_id, id, text, weight) VALUES (?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, question.getId().toString());
                preparedStatement.setString(2, answer.getId().toString());
                preparedStatement.setString(3, answer.getAnswerText());
                preparedStatement.setDouble(4, answer.getWeight());
                preparedStatement.execute();

                QuestionService questionService = new QuestionService();
                questionService.verifyQuestion(question);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                Connector.close(connection, preparedStatement);
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Question question, List<Answer> answers) {
        for (Answer answer : answers) {
            add(question, answer);
        }

        QuestionService questionService = new QuestionService();
        questionService.verifyQuestion(question);
        return true;
    }

//    private void updateAnswersWeight(Question question, Answer answer) {
//        QuestionService questionService = new QuestionService();
//        AnswerService answerService = new AnswerService();
//        List<Answer> answersForQuestion = questionService.getAnswersForQuestion(question);
//        questionService.recalculateAnswersWeight(question, answer);
//        for (Answer a : answersForQuestion) {
//            answerService.update(a);
//        }
//    }

    @Override
    public boolean remove(UUID id) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM answers WHERE id = ?";
        try {
            String[] keys = {"question_id"};

            preparedStatement = connection.prepareStatement(sql, keys);
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                String question_id = generatedKeys.getString(keys[0]);
                QuestionService questionService = new QuestionService();
                Question question = questionService.getById(UUID.fromString(question_id));
                questionService.verifyQuestion(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public void removeAll(List<Answer> answers) {
        for (Answer answer : answers) {
            remove(answer.getId());
        }
    }

    @Override
    public boolean update(Answer newAnswer) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE answers SET text = ?, weight = ? WHERE id = ?";
        try {
            String[] keys = {"question_id"};

            preparedStatement = connection.prepareStatement(sql, keys);
            preparedStatement.setString(1, newAnswer.getAnswerText());
            preparedStatement.setDouble(2, newAnswer.getWeight());
            preparedStatement.setString(3, newAnswer.getId().toString());
            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                String question_id = generatedKeys.getString(keys[0]);
                QuestionService questionService = new QuestionService();
                Question question = questionService.getById(UUID.fromString(question_id));
                questionService.verifyQuestion(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public void updateAll(List<Answer> newAnswers) {
        for (Answer newAnswer : newAnswers) {
            update(newAnswer);
        }
    }

    @Override
    public List<Answer> getAllByQuestion(Question question) {
        List<Answer> answers = new ArrayList<>();

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, text, weight FROM answers WHERE question_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getId().toString());
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
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return answers;
    }

    @Override
    public Answer getById(UUID id) {
        Answer answer = null;

        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT text, weight FROM answers WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                answer = new Answer();
                answer.setId(id);
                answer.setAnswerText(resultSet.getString("text"));
                answer.setWeight(resultSet.getDouble("weight"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return answer;
    }

    public boolean isDefault(String id){
        return id.equals("000e0000-e00b-0d0-a000-000000000000");
    }
}
