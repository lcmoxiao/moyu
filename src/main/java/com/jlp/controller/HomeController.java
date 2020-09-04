package com.jlp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    void defualt(HttpServletResponse response) throws IOException {
        response.sendRedirect("/visit/main.html");
    }

    @RequestMapping(value = "/hello")
    String hello() {
        return "/hello";
    }

    @RequestMapping(value = "/login")
    String login() {
        return "/login";
    }

    @RequestMapping(value = "/IPerror")
    String IPerror() {
        return "/IPerror";
    }
}
