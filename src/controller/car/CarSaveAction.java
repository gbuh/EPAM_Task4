package controller.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Model;
import domain.Condition;
import domain.Car;
import service.ServiceException;
import service.CarService;
import util.FactoryException;

public class CarSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Car car = new Car();
        try {
            car.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException e) {
            e.printStackTrace();
            System.out.println("\nERROR\n");
        }
        try {
            car.setModel(Model.values()[Integer.parseInt(req.getParameter("model"))]);
            car.setPlaces(Byte.parseByte(req.getParameter("places")));
            car.setCarrying(Byte.parseByte(req.getParameter("carrying")));
            car.setCondition(Condition.values()[Integer.parseInt(req.getParameter("condition"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
        if(car.getModel() != null && car.getCondition() != null) {
            try {
                CarService service = getServiceFactory().getCarService();
                service.save(car);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/car/list.html");
    }
}