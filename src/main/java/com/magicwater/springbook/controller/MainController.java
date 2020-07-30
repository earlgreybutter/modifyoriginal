package com.magicwater.springbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/state")
public class MainController {
    
    @GetMapping("/about")
    public ModelAndView getAbout(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("state/about");
        return mav;
    }

    @GetMapping("/contact")
    public ModelAndView getContact(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("state/contact");
        return mav;
    }

    @GetMapping("/menu")
    public ModelAndView getMenu(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("state/menu");
        return mav;
    }

}