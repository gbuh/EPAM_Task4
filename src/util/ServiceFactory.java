package util;

import java.sql.Connection;

import dao.UserDao;
import dao.RequestDao;
import service.UserService;
import service.RequestService;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;

    RequestService getRequestService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    RequestDao getRequestDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}