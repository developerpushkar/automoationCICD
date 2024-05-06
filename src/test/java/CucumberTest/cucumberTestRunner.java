package CucumberTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/CucumberTest", glue="SSOL_StepDefination.stepDefinations",
monochrome=true,  plugin= {"html:target/cucumber.html"})

////
//@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinitions",
//monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})

public class cucumberTestRunner extends AbstractTestNGCucumberTests{

}
