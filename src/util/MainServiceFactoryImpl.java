package util;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import dao.RequestDao;
import dao.mysql.UserDaoImpl;
import dao.mysql.RequestDaoImpl;
import service.UserService;
import service.RequestService;
import service.logic.UserServiceImpl;
import service.logic.RequestServiceImpl;

public class MainServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("12345");
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public RequestService getRequestService() throws FactoryException {
        RequestServiceImpl requestService = new RequestServiceImpl();
        requestService.setRequestDao(getRequestDao());
        return requestService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public RequestDao getRequestDao() throws FactoryException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        requestDao.setConnection(getConnection());
        return requestDao;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = Connector.getConnection();
            } catch(SQLException e) {
                throw new FactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {}
    }
}