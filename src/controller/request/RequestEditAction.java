package controller.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Request;
import domain.Status;
import service.RequestService;
import service.ServiceException;
import util.FactoryException;

public class RequestEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                RequestService service = getServiceFactory().getRequestService();
                Request request = service.findById(id);
                req.setAttribute("request", request);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("statuses", Status.values());
        return null;
    }
}