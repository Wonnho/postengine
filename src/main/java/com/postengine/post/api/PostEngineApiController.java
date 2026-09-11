package com.postengine.post.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostEngineApiController {
    @GetMapping("/api/chao")
    public String chao() {
        return "Chao";
    }

}
