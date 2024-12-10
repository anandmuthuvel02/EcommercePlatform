package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="stepdefenition",
monochrome=true, tags = "@Regression",plugin= {"html:reports/cucmber.html"})

public class TestNGCucumberTestRunner extends AbstractTestNGCucumberTests   {
	
	
}
