package com.example.part3.controller;

import com.example.part3.domain.HistorySmoke;
import com.example.part3.domain.Smoke;
import com.example.part3.repos.HistorySmokeRepo;
import com.example.part3.repos.SmokeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SmokeController {

    @Autowired
    SmokeRepo smokeRepo;

    @Autowired
    HistorySmokeRepo historySmokeRepo;



    @GetMapping(value = "/smoke")
    public String smoke (Map<String, Object> model) {
        SmokeControllerMethod (model);
        return "smoke";
    }

    private void SmokeControllerMethod ( Map<String, Object> model ){
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        Iterable<Smoke> smokes = smokeRepo.findAll ( );
        Iterable<HistorySmoke> historySmokes = historySmokeRepo.findAll ( );
        model.put ("smokes", smokes);
        model.put ("historySmokes", historySmokes);
        model.put ("user", user);
    }

    @GetMapping(value = "/smokeEnglish")
    public String smokeEnglish (Map<String, Object> model) {
        SmokeControllerMethod (model);
        return "smokeEnglish";
    }

    @GetMapping(value = "/mainSmoke")
    public String mainSmoke (Map<String, Object> model) {
        SmokeControllerMethod (model);
        return "mainSmoke";
    }

    @GetMapping(value = "/mainSmokeEnglish")
    public String mainSmokeEnglish (Map<String, Object> model) {
        SmokeControllerMethod (model);
        return "mainSmokeEnglish";
    }


    @PostMapping("filter2")
    public String filter2 ( @RequestParam String filter2, Map<String, Object> model) throws ParseException{
        SmokeControllerFilterMethod (filter2, model);
        return "mainSmoke";
    }

    private void SmokeControllerFilterMethod ( @RequestParam String filter2, Map<String, Object> model ) throws ParseException{
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        Iterable<HistorySmoke> historySmokes;
        if (filter2 != null && !filter2.isEmpty ( ) && filter2 != "") {
            SimpleDateFormat dt = new SimpleDateFormat ("yyyy-MM-dd");
            Date date = dt.parse (filter2);
            historySmokes = historySmokeRepo.findByLocalDate (date);
        } else {
            historySmokes = historySmokeRepo.findAll ( );
        }
        model.put ("historySmokes", historySmokes);
        model.put ("user", user);
    }

    @PostMapping("filter3")
    public String filter3 ( @RequestParam String filter3, Map<String, Object> model) throws ParseException{
        SmokeControllerFilterMethod (filter3, model);
        return "smoke";
    }
}



