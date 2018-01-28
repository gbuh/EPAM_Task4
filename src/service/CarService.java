package service;

import java.util.List;

import domain.Car;

public interface CarService {
    List<Car> findAll() throws ServiceException;

    Car findById(Long id) throws ServiceException;

    void save(Car request) throws ServiceException;

    void delete(Long id) throws ServiceException;
}