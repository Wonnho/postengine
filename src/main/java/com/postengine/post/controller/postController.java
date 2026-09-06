package com.postengine.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class postController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username","everybody");
        return "greetings";// return greetings.mustache
    }

    @GetMapping("/bye")
    public String byeBye(Model model){
          model.addAttribute("username","Lioness");
        return "goodbye";
      }
}
