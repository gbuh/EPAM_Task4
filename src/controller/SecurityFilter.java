package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Role;
import domain.User;

public class SecurityFilter implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMIN);
        Set<Role> employees = new HashSet<>();
        employees.add(Role.DISPATCHER);
        employees.add(Role.DRIVER);
        Set<Role> dispatcher = new HashSet<>();
        dispatcher.add(Role.DISPATCHER);
        Set<Role> driver = new HashSet<>();
        driver.add(Role.DRIVER);

//        permissions.put("/", null);
//        permissions.put("/index", null);
//        permissions.put("/login", null);

        permissions.put("/logout", all);
        permissions.put("/password/edit", all);
        permissions.put("/password/save", all);

        permissions.put("/user/list", admin);
        permissions.put("/user/edit", admin);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/car/list", employees);
        permissions.put("/request/list", employees);
        permissions.put("/request/view", employees);
        permissions.put("/request/edit", dispatcher);
        permissions.put("/request/save", dispatcher);
        permissions.put("/request/delete", dispatcher);
        permissions.put("/car/edit", driver);
        permissions.put("/car/save", driver);
        permissions.put("/car/delete", driver);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                User user = (User)session.getAttribute("currentUser");
                if(user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context + "/login.html?message=login.message.access.denied");
    }

    @Override
    public void destroy() {}
}