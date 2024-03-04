package com.SpringBoot.springbootRef.cucumber.stepsdef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.SpringBoot.springbootRef.cucumber.stepsdef"
)
public class CucumberTest {
    @Test
    public void contextLoads() {
        // This method is intentionally empty. It is used as a placeholder for the Cucumber tests.
    }
}