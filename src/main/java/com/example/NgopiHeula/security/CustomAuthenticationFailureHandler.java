package com.example.NgopiHeula.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
//        response.sendRedirect(request.getContextPath()+"/login/loginFailed");
        var link = "/login/loginForm";
        response.sendRedirect(request.getContextPath()+ link + "?error=" + "Username or Password invalid");
    }
}
