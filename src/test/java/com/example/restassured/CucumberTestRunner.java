package com.example.restassured;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = "com.example.restassured", // Package containing step definitions
    plugin = {
        "pretty", 
        "html:target/cucumber-reports.html", // Generates an HTML report
        "json:target/cucumber-reports/cucumber.json", // Generates a JSON report for integration with other tools
        "junit:target/cucumber-reports/cucumber.xml" // Generates a JUnit report for CI tools
    },
    monochrome = true // Makes the console output more readable
)
public class CucumberTestRunner {
}