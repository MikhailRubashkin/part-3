package com.example.part3.controller;

import com.example.part3.repos.HistoryMessageRepo;
import com.example.part3.repos.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ScheduleController {

    @Autowired
    private TemperatureRepo temperatureRepo;

    @Autowired
    private HistoryMessageRepo historyMessageRepo;


    @GetMapping("/schedule")
    public String schedule ( Map<String, Object> model ){
        scheduleMethod (model);
        return "schedule";
    }

    private void scheduleMethod ( Map<String, Object> model ){
        MainController.FilterMainMethod (model, temperatureRepo, historyMessageRepo);
    }

    @GetMapping("/scheduleEnglish")
    public String scheduleEnglish ( Map<String, Object> model ){
        scheduleMethod (model);
        return "scheduleEnglish";
    }

}
