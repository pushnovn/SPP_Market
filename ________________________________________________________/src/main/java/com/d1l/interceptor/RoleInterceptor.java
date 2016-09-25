package com.d1l.interceptor;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.model.Role;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class RoleInterceptor extends AbstractInterceptor {
    private List<String> allowedRoles = new ArrayList<String>();
    private List<String> disallowedRoles = new ArrayList<String>();

    public void setAllowedRoles(String roles) {
        this.allowedRoles = stringToList(roles);
    }

    public void setDisallowedRoles(String roles) {
        this.disallowedRoles = stringToList(roles);
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String result = null;
        if (!isAllowed(request, invocation.getAction())) {
            response.sendRedirect("/");
            //result = handleRejection(invocation, response);
        } else {
            result = invocation.invoke();
        }
        return result;
    }

    public List<String> stringToList(String val) {
        if (val != null) {
            String[] list = val.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public boolean isAllowed(HttpServletRequest request, Object action) throws Exception {

        HttpSession session=request.getSession(false);
        boolean result = false;
        String loginRole = null;

        if (session.getAttribute("login")!=null) {
            String login = (String) session.getAttribute("login");
            session.setAttribute("role", (UserDao.getUserByLogin(login) != null) ? UserDao.getUserByLogin(login).getRole().getName() : null);
        }

        if(session != null){
            loginRole=(String)session.getAttribute("role");
        }

        if (allowedRoles.size() > 0) {
            if(session == null || loginRole==null){
                return result;
            }
            for (String role : allowedRoles) {
                if (role.equalsIgnoreCase(loginRole)) {
                    result = true;
                }
            }
            return result;
        } else if (disallowedRoles.size() > 0) {
            if(session != null || loginRole != null){
                for (String role : disallowedRoles) {
                    if (role.equalsIgnoreCase(loginRole)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String handleRejection(ActionInvocation invocation,
                                  HttpServletResponse response) throws Exception {
        // response.sendRedirect("/");
        return "invalidAdminAccess";
    }

}
