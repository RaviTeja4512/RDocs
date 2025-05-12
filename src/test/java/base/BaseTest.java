package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();

    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }



    public Properties propertiesCode() throws Exception
    {
        final String location = "C:\\Users\\rpl02\\OneDrive\\Documents\\Playwright\\src\\test\\resources\\configuration.properties";
        FileInputStream fs =new FileInputStream(location);
        Properties prop = new Properties();
        prop.load(fs);
        return prop;
    }
}