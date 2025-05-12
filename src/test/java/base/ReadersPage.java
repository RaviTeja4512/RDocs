package base;

import com.microsoft.playwright.Page;
import locators.XeroxRPDLocators;
import org.testng.annotations.Test;
import tests.XeroxRPD;


public class ReadersPage  {
    XeroxRPD x=new XeroxRPD();
    Page page;
    @Test
    public void readersPage() throws Exception {

        page.click(XeroxRPDLocators.dropdown);
        page.click(XeroxRPDLocators.actualsize);
        page.click(XeroxRPDLocators.fitpage);
        page.click(XeroxRPDLocators.fullwidth);
        page.click(XeroxRPDLocators.ZOOM50);
        page.click(XeroxRPDLocators.ZOOM75);
        page.click(XeroxRPDLocators.ZOOM100);
        page.click(XeroxRPDLocators.ZOOM125);
        page.click(XeroxRPDLocators.ZOOM150);
        page.click(XeroxRPDLocators.SideNote);
        String s=page.locator(XeroxRPDLocators.SideNote1).textContent();
        System.out.println(s);
        page.click(XeroxRPDLocators.ReplyToDocument);
        page.fill(XeroxRPDLocators.Subject, "Xerox RPD");
        page.fill(XeroxRPDLocators.Message, "Xerox RPD");
        page.click(XeroxRPDLocators.Send);
        page.click(XeroxRPDLocators.close);
    }

}
