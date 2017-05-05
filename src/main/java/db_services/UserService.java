package db_services;

import dao.UserDAO;
import db_connection.Connector;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserService implements UserDAO {

    @Override
    public void add(User user) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users(id, login, password) VALUES (?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
            Connector.close(connection, preparedStatement);
        }
    }

    @Override
    public User getByLogin(String login) {
        User user = new User();
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.close(connection, preparedStatement);
        }
        return user;
    }
}
