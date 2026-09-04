package com.postengine.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class postController {

    @GetMapping("/hi")
    public String niceToMeetYou() {
        return "greetings";// return greetings.mustache
    }
}
