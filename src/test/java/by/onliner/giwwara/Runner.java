package by.onliner.giwwara;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

import java.util.stream.Stream;

@CucumberOptions(
            features = {"src/test/resources/features"},
            glue = {"by/onliner/giwwara/business_functionality/steps"},
            plugin = {"pretty"},
            tags = {"@test1", "@test2"}
    )

    public final class Runner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    }
