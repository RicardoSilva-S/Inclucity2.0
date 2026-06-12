package com.inclucity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller da página inicial do IncluCity.
 * Responsabilidade única: servir a view da home.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
