package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.RequestDao;
import dao.DaoException;
import domain.Request;
import domain.Status;

public class RequestDaoImpl extends BaseDaoImpl implements RequestDao {
    @Override
    public Request read(Long id) throws DaoException {
        String sql = "SELECT request.request_id, " + 
                "       request.driver_id," + 
                "       request.description," + 
                "       user.last_name," + 
                "       CONCAT(user.last_name,' ',user.first_name,' ',user.middle_name) AS driverName," + 
                "       car.model," + 
                "       car.places," + 
                "       car.carrying," + 
                "       request.status" + 
                "       FROM `request`" + 
                "       INNER JOIN `user` ON user.user_id = request.driver_id" + 
                "       INNER JOIN `driver` ON user.user_id = driver.driver_id" + 
                "       INNER JOIN `car` ON car.car_id = driver.car_id WHERE request_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Request request = null;
            if(resultSet.next()) {
                request = new Request();
                request.setId(id);
                request.setDescription(resultSet.getString("description"));
                request.setDriverId(resultSet.getLong("driver_id"));
                request.setStatus(Status.values()[resultSet.getInt("status")]);
            }
            return request;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Request> readAll() throws DaoException {
        String sql = "SELECT `request_id`, `driver_id`, `description`, `status` FROM `request`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Request> requests = new ArrayList<>();
            while(resultSet.next()) {
                Request request = new Request();
                request.setId(resultSet.getLong("request_id"));
                request.setDescription(resultSet.getString("description"));
                request.setDriverId(resultSet.getLong("driver_id"));
                request.setStatus(Status.values()[resultSet.getInt("status")]);
                requests.add(request);
            }
            return requests;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Request request) throws DaoException {
        String sql = "INSERT INTO `request` (`driver_id`, `description`, `status`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, request.getDriverId());
            statement.setString(2, request.getDescription());
            statement.setInt(3, request.getStatus().ordinal());
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
    public void update(Request request) throws DaoException {
        String sql = "UPDATE `request` SET `driver_id` = ?, `description` = ?, `status` = ? WHERE `request_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, request.getDriverId());
            statement.setString(2, request.getDescription());
            statement.setInt(3, request.getStatus().getIndex());
            statement.setLong(4, request.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `request` WHERE `request_id` = ?";
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