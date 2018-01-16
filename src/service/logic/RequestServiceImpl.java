package service.logic;

import java.util.List;

import dao.RequestDao;
import dao.DaoException;
import domain.Request;
import service.RequestService;
import service.ServiceException;

public class RequestServiceImpl implements RequestService {
    private RequestDao requestDao;

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public List<Request> findAll() throws ServiceException {
        try {
            return requestDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Request findById(Long id) throws ServiceException {
        try {
            return requestDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}