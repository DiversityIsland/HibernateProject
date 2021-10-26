package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService dao = new UserServiceImpl();
        List<User> users = null;

        dao.createUsersTable();
        dao.saveUser("Casey", "Jones", (byte) 24);
        dao.saveUser("Davy", "Jones", (byte) 103);
        dao.saveUser("Robin", "Hood", (byte) 36);
        dao.saveUser("Freddy", "Krueger", (byte) 45);
        users = dao.getAllUsers();
        users.forEach(System.out::println);
        dao.cleanUsersTable();
        dao.dropUsersTable();
    }
}
