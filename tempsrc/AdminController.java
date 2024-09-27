package source.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}