package test.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.UserDao;
import dao.mysql.UserDaoImpl;
import domain.Role;
import domain.User;
import util.Connector;

public class UserDaoTest {
    public static void readTest(UserDao userDao) throws DaoException {
        User user = userDao.read(1L);
        output(user);
    }

    public static void readByLoginTest(UserDao userDao) throws DaoException {
        User user = userDao.readByLogin("диспетчер1");
        output(user);
    }

    public static void readAllTest(UserDao userDao) throws DaoException {
        List<User> users = userDao.readAll();
        for(User user : users) {
            output(user);
        }
    }

    public static Long createTest(UserDao userDao) throws DaoException {
        User user = new User();
        user.setLogin("NEW-user");
        user.setPassword("NEW-password");
        user.setLastName("NEW-LastName");
        user.setFirstName("NEW-FirstName");
        user.setMiddleName("NEW-MiddleName");
        user.setRole(Role.ADMIN);
        Long id = userDao.create(user);
        System.out.printf("\tUser successfully added with id=%d\n", id);
        return id;
    }

    public static void updateTest(UserDao userDao, Long id) throws DaoException {
        User user = new User();
        user.setId(id);
        user.setLogin("CHANGED-user");
        user.setPassword("CHANGED-password");
        user.setLastName("CHANGED-LastName");
        user.setFirstName("CHANGED-FirstName");
        user.setMiddleName("CHANGED-MiddleName");
        user.setRole(Role.DRIVER);
        userDao.update(user);
        System.out.println("\tUser was successfully updated");
    }

    public static void deleteTest(UserDao userDao, Long id) throws DaoException {
        userDao.delete(id);
        System.out.println("\tUser was successfully deleted");
    }

    public static void main(String[] args) {
        TestInitializator.init();
        UserDaoImpl userDao = new UserDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            userDao.setConnection(connection);
            System.out.println("UserDao.read(1L):");
            readTest(userDao);
            System.out.println("UserDao.readByLogin('диспетчер1'):");
            readByLoginTest(userDao);
            System.out.println("UserDao.readAll():");
            readAllTest(userDao);
            System.out.println("UserDao.create():");
            Long id = createTest(userDao);
            System.out.println("UserDao.readAll():");
            readAllTest(userDao);
            System.out.println("UserDao.update():");
            updateTest(userDao, id);
            System.out.println("UserDao.readAll():");
            readAllTest(userDao);
            System.out.println("UserDao.delete():");
            deleteTest(userDao, id);
            System.out.println("UserDao.readAll():");
            readAllTest(userDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(User user) {
        System.out.printf("\tuser_id=%d, login=%s, last_name=%s,"
                + " first_name=%s, middle_name=%s, password=%s, role=%s\n",
                user.getId(),
                user.getLogin(),
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getPassword(),
                user.getRole().getName());
    }
}