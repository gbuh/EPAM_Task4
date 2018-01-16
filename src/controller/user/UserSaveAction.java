package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.Forward;
import domain.Role;
import domain.User;
import service.ServiceException;
import service.UserService;
import util.FactoryException;

public class UserSaveAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        req.setCharacterEncoding("UTF-8");
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        user.setLogin(req.getParameter("login"));
        user.setLastName(req.getParameter("last_name"));
        user.setFirstName(req.getParameter("first_name"));
        user.setMiddleName(req.getParameter("middle_name"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
        if(user.getLogin() != null && user.getRole() != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                service.save(user);
            } catch(FactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/user/list.html");
    }
}