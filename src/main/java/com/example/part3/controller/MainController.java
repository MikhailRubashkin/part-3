package com.example.part3.controller;

import com.example.part3.domain.HistoryMessage;
import com.example.part3.domain.Temperature;
import com.example.part3.repos.HistoryMessageRepo;
import com.example.part3.repos.TemperatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private TemperatureRepo temperatureRepo;

    @Autowired
    private HistoryMessageRepo historyMessageRepo;


    @GetMapping("/")
    public String greeting ( Model model ){
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        model.addAttribute ("user", user);
        return "greeting";
    }

    @GetMapping("/greetingEnglish")
    public String greetingEnglish ( Model model ){
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        model.addAttribute ("user", user);
        return "greetingEnglish";
    }


    @GetMapping("/main")
    public String main ( Map<String, Object> model ){
        MainControllerMethod (model);
        return "main";
    }

    private void MainControllerMethod ( Map<String, Object> model ){
        FilterMainMethod (model, temperatureRepo, historyMessageRepo);
    }

    static void FilterMainMethod ( Map<String, Object> model, TemperatureRepo temperatureRepo, HistoryMessageRepo historyMessageRepo ){
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        Iterable<Temperature> messages = temperatureRepo.findAll ( );
        Iterable<HistoryMessage> historyMessages = historyMessageRepo.findAll ( );
        model.put ("messages", messages);
        model.put ("historyMessages", historyMessages);
        model.put ("user", user);
    }

    @GetMapping("/mainEnglish")
    public String mainEnglish ( Map<String, Object> model ){
        MainControllerMethod (model);

        return "mainEnglish";
    }

    @PostMapping("filter")
    public String filter ( @RequestParam String filter, Map<String, Object> model ) throws ParseException{
        MainFilterMethod (filter, model);
        return "schedule";
    }

    private void MainFilterMethod ( @RequestParam String filter, Map<String, Object> model ) throws ParseException{
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        Iterable<HistoryMessage> historyMessages;
        if (filter != null && !filter.isEmpty ( ) && filter != "") {
            SimpleDateFormat dt = new SimpleDateFormat ("yyyy-MM-dd");
            Date date = dt.parse (filter);
            historyMessages = historyMessageRepo.findByLocalDate (date);
        } else {
            historyMessages = historyMessageRepo.findAll ( );
        }
        model.put ("historyMessages", historyMessages);
        model.put ("user", user);
    }

    @PostMapping("filter4")
    public String filter4 ( @RequestParam String filter4, Map<String, Object> model ) throws ParseException{
        MainFilterMethod (filter4, model);
        return "main";
    }
}
