package test.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DaoException;
import dao.RequestDao;
import dao.mysql.RequestDaoImpl;
import domain.Request;
import domain.Status;
import util.Connector;

public class RequestDaoTest {
    public static void readTest(RequestDao requestDao) throws DaoException {
        Request request = requestDao.read(1L);
        output(request);
    }

    public static void readByLoginTest(RequestDao requestDao) throws DaoException {
        Request request = requestDao.read(3L);
        output(request);
    }

    public static void readAllTest(RequestDao requestDao) throws DaoException {
        List<Request> requests = requestDao.readAll();
        for(Request request : requests) {
            output(request);
        }
    }

    public static Long createTest(RequestDao requestDao) throws DaoException {
        Request request = new Request();
        request.setDriverId(5L);
        request.setDescription("NEW-trip");
        request.setStatus(Status.ACCEPT);
        Long id = requestDao.create(request);
        System.out.printf("\tRequest successfully added with id=%d\n", id);
        return id;
    }

    public static void updateTest(RequestDao requestDao, Long id) throws DaoException {
        Request request = new Request();
        request.setId(id);
        request.setDriverId(5L);
        request.setDescription("CHANGED-trip");
        request.setStatus(Status.DONE);
        requestDao.update(request);
        System.out.println("\tRequest was successfully updated");
    }

    public static void deleteTest(RequestDao requestDao, Long id) throws DaoException {
        requestDao.delete(id);
        System.out.println("\tRequest was successfully deleted");
    }

    public static void main(String[] args) {
        TestInitializator.init();
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            requestDao.setConnection(connection);
            System.out.println("RequestDao.read(1L):");
            readTest(requestDao);
            System.out.println("RequestDao.readByLogin('3'):");
            readByLoginTest(requestDao);
            System.out.println("RequestDao.readAll():");
            readAllTest(requestDao);
            System.out.println("RequestDao.create():");
            Long id = createTest(requestDao);
            System.out.println("RequestDao.readAll():");
            readAllTest(requestDao);
            System.out.println("RequestDao.update():");
            updateTest(requestDao, id);
            System.out.println("RequestDao.readAll():");
            readAllTest(requestDao);
            System.out.println("RequestDao.delete():");
            deleteTest(requestDao, id);
            System.out.println("RequestDao.readAll():");
            readAllTest(requestDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }

    private static void output(Request request) {
        System.out.printf("\trequest_id=%d, driver_id=%s, description=%s, status=%s\n",
                request.getId(),
                request.getDriverId(),
                request.getDescription(),
                request.getStatus().getName());
    }
}