package controller.request;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Request;
import service.RequestService;
import service.ServiceException;
import util.FactoryException;

public class RequestListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            RequestService service = getServiceFactory().getRequestService();
            List<Request> requests = service.findAll();
            req.setAttribute("requests", requests);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}