package com.devxminds.donpipe.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/login")
    public String home() {
        return "main/com/devxminds/donpipe/resources/login";
    }
}
