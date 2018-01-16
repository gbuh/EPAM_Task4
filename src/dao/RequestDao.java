package dao;

import java.util.List;

import domain.Request;

public interface RequestDao extends Dao<Request> {
    List<Request> readAll() throws DaoException;
}