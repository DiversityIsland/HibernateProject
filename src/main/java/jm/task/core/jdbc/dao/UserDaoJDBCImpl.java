package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SAVE = "INSERT INTO users(name, lastName, age) VALUES(?, ?, ?)";
    private static final String REMOVE = "DELETE FROM users WHERE id = ?";
    private static final String GET = "SELECT * FROM users";
    private static final String CREATE = "CREATE TABLE users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(99) NOT NULL, lastName VARCHAR(99) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id))";
    private static final String DROP = "DROP TABLE users";
    private static final String CLEAN = "DELETE FROM users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;

        try (Connection connection = Util.getConnect()) {
            statement = connection.createStatement();

            statement.execute(CREATE);
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        Statement statement = null;

        try (Connection connection = Util.getConnect()) {
            statement = connection.createStatement();

            statement.execute(DROP);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;

        try (Connection connection = Util.getConnect()) {
            preparedStatement = connection.prepareStatement(SAVE);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }

        System.out.printf("User с именем \"%s\" добавлен в базу данных!", name);
        System.out.println();
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        try (Connection connection = Util.getConnect()) {
            preparedStatement = connection.prepareStatement(REMOVE);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Statement statement = null;

        try (Connection connection = Util.getConnect()) {
            statement = connection.createStatement();

            ResultSet res = statement.executeQuery(GET);

            while (res.next()) {
                Long id = res.getLong("id");
                User user = new User(res.getString("name"),
                                    res.getString("lastName"),
                                    res.getByte("age"));
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {

        }

        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;

        try (Connection connection = Util.getConnect()) {
            statement = connection.createStatement();

            statement.executeUpdate(CLEAN);
        } catch (SQLException e) {

        }
    }
}
