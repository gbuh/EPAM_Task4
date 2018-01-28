package service.logic;

import java.util.List;

import dao.CarDao;
import dao.DaoException;
import domain.Car;
import service.CarNotExistsException;
import service.CarService;
import service.ServiceException;

public class CarServiceImpl implements CarService {
    private CarDao carDao;

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<Car> findAll() throws ServiceException {
        try {
            return carDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Car findById(Long id) throws ServiceException {
        try {
            return carDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Car car) throws ServiceException {
        try {
            if(car.getId() != null) {
                Car storedCar = carDao.read(car.getId());
                if(storedCar != null) {
                    carDao.update(car);
                } else {
                    throw new CarNotExistsException(car.getId());
                }
            } else {
                Long id = carDao.create(car);
                car.setId(id);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            carDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}