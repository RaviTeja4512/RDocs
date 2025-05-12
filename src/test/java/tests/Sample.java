package tests;

import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Properties;

public class Sample extends BaseTest
{


public void sample() throws Exception
{
    Properties prop = propertiesCode();
    page.navigate("https://www.mailinator.com");
    page.fill("//*[@id='search']","test0505@mailinator.com");
    page.click("//*[text()='GO']");
    page.pause();
}

}
