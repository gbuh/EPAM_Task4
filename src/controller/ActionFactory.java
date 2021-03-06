package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import controller.car.CarDeleteAction;
import controller.car.CarEditAction;
import controller.car.CarListAction;
import controller.car.CarSaveAction;
import controller.password.PasswordSaveAction;
import controller.request.RequestDeleteAction;
import controller.request.RequestViewAction;
import controller.request.RequestEditAction;
import controller.request.RequestSaveAction;
import controller.request.RequestListAction;
import controller.user.UserDeleteAction;
import controller.user.UserEditAction;
import controller.user.UserListAction;
import controller.user.UserSaveAction;

public class ActionFactory {
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/password/save", PasswordSaveAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/car/list", CarListAction.class);
        actions.put("/car/edit", CarEditAction.class);
        actions.put("/car/save", CarSaveAction.class);
        actions.put("/car/delete", CarDeleteAction.class);
        actions.put("/request/list", RequestListAction.class);
        actions.put("/request/view", RequestViewAction.class);
        actions.put("/request/edit", RequestEditAction.class);
        actions.put("/request/save", RequestSaveAction.class);
        actions.put("/request/delete", RequestDeleteAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        if(action != null) {
            try {
                return (Action)action.newInstance();
            } catch(InstantiationException | IllegalAccessException | NullPointerException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}