package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import java.util.Properties;

public class RDocsLogin extends BaseTest
{
    @Test
    public void login() throws Exception {
        Properties prop = propertiesCode();
        page.navigate(prop.getProperty("staging_url"));
        page.fill("(//*[@name='email'])[1]",prop.getProperty("staging_username"));
        page.fill("//input[@placeholder='Password']",prop.getProperty("staging_password"));
        page.click("//button[text()='Sign in']");
    }

}
