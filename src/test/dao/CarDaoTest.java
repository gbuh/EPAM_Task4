package test.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.CarDao;
import dao.mysql.CarDaoImpl;
import domain.Car;
import domain.Model;
import domain.Condition;
import util.Connector;

public class CarDaoTest {
    public static void readTest(CarDao carDao) throws DaoException {
        Car car = carDao.read(1L);
        output(car);
    }

    public static void readByLoginTest(CarDao carDao) throws DaoException {
        Car car = carDao.read(3L);
        output(car);
    }

    public static void readAllTest(CarDao carDao) throws DaoException {
        List<Car> cars = carDao.readAll();
        for(Car car : cars) {
            output(car);
        }
    }

    public static Long createTest(CarDao carDao) throws DaoException {
        Car car = new Car();
        car.setModel(Model.BUS);
        car.setPlaces((byte)25);
        car.setCarrying((byte)0);
        car.setCondition(Condition.DEFECTIVE);
        Long id = carDao.create(car);
        System.out.printf("\tCar successfully added with id=%d\n", id);
        return id;
    }

    public static void updateTest(CarDao carDao, Long id) throws DaoException {
        Car car = new Car();
        car.setId(id);
        car.setModel(Model.TRUCK);
        car.setPlaces((byte)0);
        car.setCarrying((byte)5);
        car.setCondition(Condition.GOOD);
        carDao.update(car);
        System.out.println("\tCar was successfully updated");
    }

    public static void deleteTest(CarDao carDao, Long id) throws DaoException {
        carDao.delete(id);
        System.out.println("\tCar was successfully deleted");
    }

    public static void main(String[] args) {
        TestInitializator.init();
        CarDaoImpl carDao = new CarDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            carDao.setConnection(connection);
            System.out.println("carDao.read(1L):");
            readTest(carDao);
            System.out.println("carDao.readByLogin('3'):");
            readByLoginTest(carDao);
            System.out.println("carDao.readAll():");
            readAllTest(carDao);
            System.out.println("carDao.create():");
            Long id = createTest(carDao);
            System.out.println("carDao.readAll():");
            readAllTest(carDao);
            System.out.println("carDao.update():");
            updateTest(carDao, id);
            System.out.println("carDao.readAll():");
            readAllTest(carDao);
            System.out.println("carDao.delete():");
            deleteTest(carDao, id);
            System.out.println("carDao.readAll():");
            readAllTest(carDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(Car car) {
        System.out.printf("\tcar_id=%d, model=%s, places=%d, carrying=%d, condition=%s\n",
                car.getId(),
                car.getModel().getName(),
                car.getPlaces(),
                car.getCarrying(),
                car.getCondition().getName());
    }
}