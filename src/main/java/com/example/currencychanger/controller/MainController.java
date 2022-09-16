package com.example.currencychanger.controller;


import com.example.currencychanger.service.NbpApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class MainController {

    private final NbpApiService apiService;

    public MainController(NbpApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("rate_list", apiService.getAllRates());
        return "index";
    }

}
