package controller.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Request;
import service.RequestService;
import service.ServiceException;
import util.FactoryException;

public class RequestViewAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            RequestService service = getServiceFactory().getRequestService();
            Request request = service.findById(id);
            if(request != null) {
                req.setAttribute("request", request);
                return null;
            } else {
                throw new NumberFormatException();
            }
        } catch(NumberFormatException e) {
            return new Forward("/request/list.html");
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}