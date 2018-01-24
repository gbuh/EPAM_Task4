package service;

import java.util.List;

import domain.Request;

public interface RequestService {
    List<Request> findAll() throws ServiceException;

    Request findById(Long id) throws ServiceException;

    void save(Request request) throws ServiceException;

    void delete(Long id) throws ServiceException;
}