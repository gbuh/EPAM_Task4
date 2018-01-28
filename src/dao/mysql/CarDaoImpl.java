package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dao.CarDao;
import dao.DaoException;
import domain.Car;
import domain.Model;
import domain.Condition;;

public class CarDaoImpl extends BaseDaoImpl implements CarDao {

    @Override
    public Car read(Long id) throws DaoException {
        String sql = "SELECT `model`, `places`, `carrying`, `condition` FROM `car` WHERE `car_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Car car = null;
            if(resultSet.next()) {
                car = new Car();
                car.setId(id);
                car.setModel(Model.values()[resultSet.getInt("model")]);
                car.setPlaces(resultSet.getByte("places"));
                car.setCarrying(resultSet.getByte("carrying"));
                car.setCondition(Condition.values()[resultSet.getInt("condition")]);
            }
            return car;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public List<Car> readAll() throws DaoException {
        String sql = "SELECT `car_id`, `model`, `places`, `carrying`, `condition` FROM `car`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Car> cars = new ArrayList<>();
            while(resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("car_id"));
                car.setModel(Model.values()[resultSet.getInt("model")]);
                car.setPlaces(resultSet.getByte("places"));
                car.setCarrying(resultSet.getByte("carrying"));
                car.setCondition(Condition.values()[resultSet.getInt("condition")]);
                cars.add(car);
            }
            return cars;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Car car) throws DaoException {
        String sql = "INSERT INTO `car` (`model`, `places`, `carrying`, `condition`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, car.getModel().ordinal());
            if(car.getPlaces() != 0) {
                statement.setByte(2, car.getPlaces());
            } else {
                statement.setNull(2, Types.TINYINT);
            }
            if(car.getCarrying() != 0) {
                statement.setByte(3, car.getCarrying());
            } else {
                statement.setNull(3, Types.TINYINT);
            }
//            statement.setByte(2, car.getPlaces());
//            statement.setByte(3, car.getCarrying());
            statement.setInt(4, car.getCondition().ordinal());
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
    public void update(Car car) throws DaoException {
        String sql = "UPDATE `car` SET `model` = ?, `places` = ?, `carrying` = ?, `condition` = ? WHERE `car_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, car.getModel().ordinal());
            if(car.getPlaces() != 0) {
                statement.setByte(2, car.getPlaces());
            } else {
                statement.setNull(2, Types.TINYINT);
            }
            if(car.getCarrying() != 0) {
                statement.setByte(3, car.getCarrying());
            } else {
                statement.setNull(3, Types.TINYINT);
            }
//            statement.setByte(2, car.getPlaces());
//            statement.setByte(3, car.getCarrying());
            statement.setInt(4, car.getCondition().ordinal());
            statement.setLong(5, car.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `car` WHERE `car_id` = ?";
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
