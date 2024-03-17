package com.itau.operacaobancaria;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/itau/operacaobancaria/steps"},
        glue = {"steps"},
        plugin = "pretty",
        monochrome = true
)
public class RunnerTest {
}
