package com.phrase.mkafka.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {

    public static final String MEMSOURCE_API_URI = "https://us.cloud.memsource.com/";

    public static final String MEMSOURCE_API_PATH = "web/api2/v1/";

    public static final Path LOGIN_FILE_PATH = Paths.get("src/main/resources/login.json");

    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())                                        // enable handling of the java.time package
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   // allow deserialization of incomplete POJOs

}
