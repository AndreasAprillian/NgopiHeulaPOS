package com.example.NgopiHeula.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlingController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode != null) {
            if (statusCode == 404) {
                return "home/404"; // Redirect ke templates/error/500.html
            } else if (statusCode == 500) {
                return "home/500"; // Redirect ke templates/error/500.html
            }
        }

        return "home/404"; // Redirect ke templates/error/error.html untuk error lain
    }
}
