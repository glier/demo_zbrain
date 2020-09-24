package com.example.demo;

import com.example.demo.controllers.EmailStorageController;
import com.example.demo.services.EmailStorageService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private EmailStorageController controller;

    @Autowired
    private EmailStorageService service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void controllerShouldWriteEmailToDB() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@email.com");

        HttpEntity<String> request =
                new HttpEntity<>(jsonObject.toString(), headers);

        assertThat(this.restTemplate.postForEntity(
                "http://localhost:" + port + "/emails/add",
                request,
                String.class).getStatusCodeValue()
        ).isEqualTo(200);

        assertThat(service.findByEmail(jsonObject.getString("email"))).isNotNull();
    }

    @Test
    void checkForDuplicateEmailWriteException() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject emailJsonObject = new JSONObject();
        emailJsonObject.put("email", "test2@email.com");

        JSONObject dupJsonObject = new JSONObject();
        dupJsonObject.put("duplicate", true);

        HttpEntity<String> request =
                new HttpEntity<>(emailJsonObject.toString(), headers);

        ResponseEntity<String> responseEntityFirst = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/emails/add",
                request,
                String.class);

        ResponseEntity<String> responseEntitySecond = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/emails/add",
                request,
                String.class);

        assertThat(responseEntityFirst.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntitySecond.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntitySecond.getBody()).isEqualTo(dupJsonObject.toString());
    }

}
