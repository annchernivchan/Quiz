package db_services;

import dao.QuestionDAO;
import db_connection.Connector;
import entities.Answer;
import entities.Question;
import entities.QuestionType;
import validation.AnswerValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionService implements QuestionDAO {

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

            if (isVerified) {
                AnswerService answerService = new AnswerService();
                answerService.addAll(question, question.getAllAnswers());
            }

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public boolean remove(Question question) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlQuestion = "DELETE FROM questions WHERE id = ?";
        String sqlAnswers = "DELETE FROM answers WHERE question_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sqlQuestion);
            preparedStatement.setString(1, question.getId().toString());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(sqlAnswers);
            preparedStatement.setString(1, question.getId().toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

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

            if (isVerified) {
                AnswerService answerService = new AnswerService();
                answerService.updateAll(question.getAllAnswers());
            }

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return true;
    }

    @Override
    public List<Question> getAll() {
        AnswerService answerService = new AnswerService();
        List<Question> questions = new ArrayList<>();

        Connection connection = Connector.getConnection();
        Statement statement = null;
        String sql = "SELECT questions.id, text, point, question_type.type FROM questions INNER JOIN question_type ON questions.question_type_id = question_type.id";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(UUID.fromString(resultSet.getString("id")));
                question.setQuestionText(resultSet.getString("text"));
                question.setPoint(resultSet.getDouble("point"));
                question.setQuestionType(QuestionType.valueOf(resultSet.getString("type")));
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

    @Override
    public Question getById(UUID id) {
        Question question = null;

        Connection connection = Connector.getConnection();
        Statement statement = null;
        String sql = "SELECT questions.id, text, point, question_type.type FROM questions INNER JOIN question_type ON question_type.id = questions.question_type_id";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                question = new Question();
                question.setId(UUID.fromString(resultSet.getString("id")));
                question.setQuestionText(resultSet.getString("text"));
                question.setPoint(resultSet.getDouble("point"));
                question.setQuestionType(QuestionType.valueOf(resultSet.getString("type")));
                question.setAllAnswers(getAnswersForQuestion(question));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.close(connection, statement);
        }
        return question;
    }

    @Override
    public List<Answer> getAnswersForQuestion(Question question) {
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
            question.setAllAnswers(answers);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return question.getAllAnswers();

    }

    @Override
    public boolean verifyQuestion(Question question) {
        QuestionService questionService = new QuestionService();
        question.setAllAnswers(questionService.getAnswersForQuestion(question));

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

//    public void recalculateAnswersWeight(Question question, Answer newAnswer) {
//        double percent = 0;
//        if (getAnswersWeight(question) == 100) {
//            percent = newAnswer.getWeight();
//        } else if (getAnswersWeight(question) + newAnswer.getWeight() >= 100) {
//            percent = getAnswersWeight(question) + newAnswer.getWeight() - 100;
//        }
//        for (Answer answer : question.getAllAnswers()) {
//            answer.setWeight(answer.getWeight() - (answer.getWeight() * percent) / 100);
//        }
//    }

    public List<Answer> getRightAnswers(List<Answer> answers) {
        for (Answer a : answers) {
            if (a.isRight()) answers.add(a);
        }
        return answers;
    }

    public double getAnswersWeight(Question question) {
        double weight = 0;
        for (Answer answer : question.getAllAnswers()) weight += answer.getWeight();
        return weight;
    }


}
