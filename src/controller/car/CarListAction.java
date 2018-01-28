package controller.car;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Car;
import service.ServiceException;
import service.CarService;
import util.FactoryException;

public class CarListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CarService service = getServiceFactory().getCarService();
            List<Car> cars = service.findAll();
            req.setAttribute("cars", cars);
            return null;
        } catch(FactoryException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}