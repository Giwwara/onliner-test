package by.onliner.giwwara.context;

import by.onliner.giwwara.util.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;


public class DriverProvider {

        private static final Logger LOGGER = LoggerFactory.getLogger(DriverProvider.class);
        private static final ThreadLocal<WebDriver> singleton = new ThreadLocal<>();

        private static boolean isDriverNotNull() {
                return singleton.get() != null;
        }

        private static boolean isDriverNull() {
                return singleton.get() == null;
        }

        /**
         * Provide WebDriver context.
         *
         * @return {@link WebDriver}
         */
        public static WebDriver webDriver() {
                if (isDriverNull()) {
                        LOGGER.info("Starting WebDriver...");
                        singleton.set(chooseDriver());
                }
                return singleton.get();
        }

        /**
         * Close session and tear down context instance.
         */
        public static void stopDriver() {
                if (isDriverNotNull()) {
                        webDriver().quit();
                        LOGGER.info("Stopping WebDriver...");
                        singleton.set(null);
                }
        }

        /**
         * Provides required context type
         *
         * @return {@link WebDriver} instance
         */
        private static WebDriver chooseDriver() {
                WebDriver driver = null;
                try {
                        if (PropertyProvider.getProperty("BROWSER_NAME").equalsIgnoreCase("Chrome")) {
                                LOGGER.info("Requested ChromeDriver...");
                                ChromeOptions options = new ChromeOptions();
                                String pathToDriver = "src/main/resources/drivers/chromedriver";
                                String os = System.getProperty("os.name");
                                if (os.contains("Mac"))
                                        pathToDriver = pathToDriver + "_mac";
                                else if (os.contains("Linux"))
                                        pathToDriver = pathToDriver + "_lin";
                                else
                                        pathToDriver = pathToDriver + "_win.exe";
                                System.setProperty("webdriver.chrome.driver", pathToDriver);
                                if (System.getProperty("SERVER_RUN") != null) {
                                        if (System.getProperty("SERVER_RUN").equals("true")) {
                                                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--no-sandbox");
                                        }
                                }
                                options.addArguments("--lang=en-us");
                                // using AbsolutePath because of issue: https://github.com/rshf/chromedriver/issues/338
                                final String path = Paths.get("src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data" + File.separator + "downloads").toAbsolutePath().toString();
                                HashMap<String, Object> chromePrefs = new HashMap<>();
                                chromePrefs.put("download.default_directory", path);
                                options.setExperimentalOption("prefs", chromePrefs);
                                LoggingPreferences logPrefs = new LoggingPreferences();
                                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                                options.setCapability("goog:loggingPrefs", logPrefs);
                                driver = new ChromeDriver(options);
                                driver.manage().window().maximize();
                        } else if (PropertyProvider.getProperty("BROWSER_NAME").equalsIgnoreCase("Safari")) {
                                LOGGER.info("Requested SafariDriver...");
                                driver = new SafariDriver();
                        }
                } catch (WebDriverException e) {
                        LOGGER.error("!!!! Driver can't be started check your configuration \n" + e.getMessage());
                }
                return driver;
        }
}