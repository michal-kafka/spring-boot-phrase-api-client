package com.phrase.mkafka.demo.consumeapi;


import com.phrase.mkafka.demo.config.Authn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URISyntaxException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private Authn authn;

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(authn.apiToken());
        return String.format("Hello %s!", name);
    }

}
