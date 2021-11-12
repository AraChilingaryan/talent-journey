package com.disqo.talent_service.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class  TestController {

    @PostMapping("/test")
    public void getMyJson(@RequestBody Map<String, Object> json) {
        System.out.println("WebHook collected JSON: " + json);
    }
}
