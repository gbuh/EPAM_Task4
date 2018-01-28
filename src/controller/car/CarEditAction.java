package controller.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Car;
import domain.Condition;
import domain.Model;
import service.ServiceException;
import service.CarService;
import util.FactoryException;

public class CarEditAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try {
                CarService service = getServiceFactory().getCarService();
                Car car = service.findById(id);
                req.setAttribute("car", car);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("models", Model.values());
        req.setAttribute("conditions", Condition.values());
        return null;
    }
}