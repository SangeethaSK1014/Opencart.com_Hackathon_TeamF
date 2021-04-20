package opencartRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
      
        features="src/test/resources/opencartFeature",
        //glue = {"src/test/java/opencartStepDefinition"} ,       
        plugin = { "pretty","com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html","html:test-output"},
        monochrome=true,
        dryRun=false
                    )

public class OpencartRunnerFile {
	

}
