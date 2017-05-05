package dao;

import entities.User;

public interface UserDAO {

    void add(User user);
    User getByLogin(String login);

}
