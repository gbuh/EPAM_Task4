package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

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
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/request/list", RequestListAction.class);
        actions.put("/request/view", RequestViewAction.class);
        actions.put("/request/edit", RequestEditAction.class);
        actions.put("/request/save", RequestSaveAction.class);
        actions.put("/request/delete", RequestDeleteAction.class);
    }

    public static Action getAction(String url) throws ServletException {
        Class<?> action = actions.get(url);
        try {
            return (Action)action.newInstance();
        } catch(InstantiationException | IllegalAccessException | NullPointerException e) {
            throw new ServletException(e);
        }
    }
}