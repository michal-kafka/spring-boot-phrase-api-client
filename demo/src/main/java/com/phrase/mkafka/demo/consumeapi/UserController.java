package com.phrase.mkafka.demo.consumeapi;

import com.phrase.mkafka.demo.config.Authn;
import com.phrase.mkafka.demo.entity.User;
import com.phrase.mkafka.demo.utils.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

import static com.phrase.mkafka.demo.utils.Constants.*;

@Controller
public class UserController {

    @Autowired
    private Authn authn;

    @Autowired
    private HttpClientService httpClientService;

    @RequestMapping("/users/{uid}")
    @ResponseBody
    private String getUser(@PathVariable Integer uid) {

        //I wanted to learn something new, so I used the RestTemplate for obtaining the JSON data from the response
        String uri = MEMSOURCE_API_URI + MEMSOURCE_API_PATH + "users/" + uid;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "ApiToken " + authn.apiToken());
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<User> responseEntity =
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<User>() {
                        }
                );

        User user = responseEntity.getBody();
        System.out.println(user);

        return "user";
    }

    @RequestMapping("/users")
    @ResponseBody
    private String getUsers() throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = httpClientService
                .getHttpClient()
                .send(httpClientService.httpGetRequest("users"), HttpResponse.BodyHandlers.ofString());

        List<User> users = httpClientService.extractListFromNode(response, "content");

        System.out.println(users);

        return "users";
    }



}
