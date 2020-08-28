package com.jlp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    String defualt() {
        return "/hello";
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
