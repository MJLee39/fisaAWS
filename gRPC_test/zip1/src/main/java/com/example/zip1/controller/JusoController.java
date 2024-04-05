package com.example.zip1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JusoController {
    @RequestMapping(value = "juso")
    public String sample(){
        return "Sample";
    }

    @RequestMapping(value = "detail")
    public String detail(){
        return "jusoPopup";
    }

    @RequestMapping(value = "juso/mobile")
    public String sampleMobile(){ return "SampleMobile"; }

    @RequestMapping(value = "detail/mobile")
    public String detailMobile(){
        return "jusoPopupMobile";
    }


    @RequestMapping(value = "juso/new")
    public String sampleNew(){
        return "apiSampleApplicationJSON";
    }
}
