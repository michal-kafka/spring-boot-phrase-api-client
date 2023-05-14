package com.phrase.mkafka.demo.consumeapi;

import com.phrase.mkafka.demo.entity.Project;
import com.phrase.mkafka.demo.utils.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class ProjectsController {

    @Autowired
    HttpClientService httpClientService;

    @RequestMapping("/projects")
    @ResponseBody
    private String getProjects() throws URISyntaxException, IOException, InterruptedException {

        HttpResponse<String> response = httpClientService
                .getHttpClient()
                .send(httpClientService.httpGetRequest("projects"), HttpResponse.BodyHandlers.ofString());

        List<Project> projects = httpClientService.extractListFromNode(response, "content");

        System.out.println(projects);

        return "project list page";
    }

}
