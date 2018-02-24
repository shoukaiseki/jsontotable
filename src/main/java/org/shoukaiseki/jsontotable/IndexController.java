package org.shoukaiseki.jsontotable;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{



    @RequestMapping(value="/index")
    public ModelAndView find(ModelMap model) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        model.addAttribute("host","www.jianshu.com");
        return mv;
    }
}

