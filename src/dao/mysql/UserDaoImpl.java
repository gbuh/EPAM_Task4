package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import dao.UserDao;
import domain.Role;
import domain.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`, `last_name`, `first_name`, `middle_name`, `role` FROM `user` WHERE `user_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT `user_id`, `password`, `last_name`, `first_name`, `middle_name`, `role` FROM `user` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        String sql = "SELECT `user_id`, `login`, `password`, `last_name`, `first_name`, `middle_name`, `role` FROM `user`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public boolean isUserExecutionsRequests(Long id) throws DaoException {
        String sql = "SELECT COUNT(*) AS `count` FROM (SELECT `request_id` FROM `request` WHERE `driver_id` = ? LIMIT 1) AS `t`";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            boolean result = true;
            if(resultSet.next()) {
                result = resultSet.getBoolean("count");
            }
            return result;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `user` (`login`, `password`, `last_name`, `first_name`, `middle_name`, `role`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getMiddleName());
            statement.setInt(6, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `user` SET `login` = ?, `password` = ?, `last_name` = ?, `first_name` = ?, `middle_name` = ?, `role` = ? WHERE `user_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getMiddleName());
            statement.setInt(6, user.getRole().ordinal());
            statement.setLong(7, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE `user_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
