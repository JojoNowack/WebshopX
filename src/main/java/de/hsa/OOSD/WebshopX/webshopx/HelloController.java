package de.hsa.OOSD.WebshopX.webshopx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloController {
    @GetMapping("/")
    public String home() {

        return "status";
    }

    @GetMapping("/greeting")
    public String greeting() {

        return "greeting";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }
}
