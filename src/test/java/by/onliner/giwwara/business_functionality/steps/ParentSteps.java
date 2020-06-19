package by.onliner.giwwara.business_functionality.steps;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import by.onliner.giwwara.context.DriverProvider;

import java.util.Date;

public class ParentSteps {

    @After
    public void embedScreenshot(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverProvider.webDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
                String browserLog = "";
                LogEntries logEntries = DriverProvider.webDriver().manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logEntries) {
                    String localBrowserLog = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
                    browserLog = browserLog + localBrowserLog + "\n";
                }
                scenario.write(browserLog);
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        DriverProvider.stopDriver();
    }
}