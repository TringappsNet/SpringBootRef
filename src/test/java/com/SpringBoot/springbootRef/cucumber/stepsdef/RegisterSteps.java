package com.SpringBoot.springbootRef.cucumber.stepsdef;

import com.SpringBoot.springbootRef.cucumber.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegisterSteps {

    private ResponseEntity<String> response;
    private User user;
    public RegisterSteps(){}
    public RegisterSteps(ResponseEntity<String>  response, User user) {
        this.response = response;
        this.user = user;
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String arg0) {
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", "Bearer your_token"); // Example header
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange("http://localhost:8080/"+arg0, HttpMethod.GET,entity, String.class);
        System.out.println(response.getBody());

    }

    @Then("the system should get all the users successfully with a status code of {int}")
    public void theSystemShouldGetAllTheUsersSuccessfullyWithAStatusCodeOf(int arg0) {
        System.out.println(response);
        Assert.assertEquals(arg0, response.getStatusCode().value());
    }

    @Given("a new user with the following details:")
    public void a_new_user_with_the_following_details(List<Map<String, String>> userData) {
        Map<String, String> userMap = userData.get(0);

        user = new User();
        user.setId(userMap.get("id"));
        user.setUsername(userMap.get("username"));
        user.setPassword(userMap.get("password"));
        user.setPhoneNumber(userMap.get("phoneNumber"));
        user.setEmail(userMap.get("email"));
        user.setProfile(userMap.get("profile"));
        user.setCsvData(userMap.get("csvData"));
        user.setVerified(userMap.get("verified"));
        //System.out.println(user.getUsername());

    }

    @When("I send a POST request to {string}")
    public void i_send_a_POST_request_to(String endpoint) throws JsonProcessingException {

        //System.out.println("http://localhost:8080"+endpoint);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJsonString = objectMapper.writeValueAsString(user);
        System.out.println(userJsonString);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", "Bearer your_token"); // Example header

        HttpEntity<String> request = new HttpEntity<>(userJsonString, headers);

        RestTemplate restTemplate = new RestTemplate();

        response = restTemplate.exchange("http://localhost:8080/api/v1/registers", HttpMethod.POST, request, String.class);

        System.out.println(response.getBody());

    }

    @Then("the system should register the user successfully with a status code of {int}")
    public void the_system_should_register_the_user_successfully_with_a_status_code_of(int statusCode) {
        System.out.println(response);
        Assert.assertEquals(statusCode, response.getStatusCode().value());
    }


    @Given("an existing user with the following details:")
    public void anExistingUserWithTheFollowingDetails(List<Map<String, String>> userData) {
        Map<String, String> userMap = userData.get(0);

        user = new User();
        user.setId(userMap.get("id"));
        user.setUsername(userMap.get("username"));
        user.setPassword(userMap.get("password"));
        user.setPhoneNumber(userMap.get("phoneNumber"));
        user.setEmail(userMap.get("email"));
        user.setProfile(userMap.get("profile"));
        user.setCsvData(userMap.get("csvData"));
        user.setVerified(userMap.get("verified"));
    }

    @When("I send a PUT request to {string}")
    public void iSendAPUTRequestTo(String arg0) {
        //user.setVerified("yes"); // set verified to "yes"

        ObjectMapper objectMapper = new ObjectMapper();
        String userJsonString = null;
        try {
            userJsonString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userJsonString);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(userJsonString, headers);
        System.out.println(user.getId());

        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange("http://localhost:8080/api/v1/registers/" + user.getId(), HttpMethod.PUT, request, String.class);

        System.out.println(response.getBody());
    }

    @Then("the system should update the user's verified status successfully with a status code of {int}")
    public void theSystemShouldUpdateTheUserSVerifiedStatusSuccessfullyWithAStatusCodeOf(int statusCode) {
        System.out.println(response);
        Assert.assertEquals(statusCode, response.getStatusCode().value());
    }

    @Given("an existing user details:")
    public void anExistingUserDetails(List<Map<String, String>> userData) {
        Map<String, String> userMap = userData.get(0);

        user = new User();
        user.setId(userMap.get("id"));
        user.setUsername(userMap.get("username"));
        user.setPassword(userMap.get("password"));
        user.setPhoneNumber(userMap.get("phoneNumber"));
        user.setEmail(userMap.get("email"));
        user.setProfile(userMap.get("profile"));
        user.setCsvData(userMap.get("csvData"));
        user.setVerified(userMap.get("verified"));
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String arg0) {
        ObjectMapper objectMapper = new ObjectMapper();
        String userJsonString = null;
        try {
            userJsonString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userJsonString);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(userJsonString, headers);
        System.out.println(user.getId());

        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange("http://localhost:8080/api/v1/registers/" + user.getId(), HttpMethod.DELETE, request, String.class);

        System.out.println(response.getBody());
    }

    @Then("the system should update the user's status successfully with a code of {int}")
    public void theSystemShouldUpdateTheUserSStatusSuccessfullyWithACodeOf(int arg0) {
        System.out.println(response);
        Assert.assertEquals(arg0, response.getStatusCode().value());
    }
}