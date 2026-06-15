package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/test/html")
    public String index(Model model) {
        model.addAttribute("title", "HLTV");
        model.addAttribute("items", List.of("NaVi", "Vitality", "G2"));
        model.addAttribute("teamsCount", 3);
        model.addAttribute("majorsCount", 12);
        model.addAttribute("online", "24k");
        return "index";
    }
}