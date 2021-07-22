package com.example.cron_demo_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class WebController {


    @GetMapping("/run")
    public String getSome() {
        System.out.println("# getSome run.....");
        return "run ...";
    }

}
