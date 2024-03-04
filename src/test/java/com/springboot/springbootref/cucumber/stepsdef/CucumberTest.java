package com.springboot.springbootref.cucumber.stepsdef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.springboot.springbootref.cucumber.stepsdef"
)
public class CucumberTest {

}
