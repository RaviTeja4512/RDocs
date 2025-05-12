    package tests;

    import base.BaseTest;
    import base.ReadersPage;
    import com.microsoft.playwright.Download;
    import com.microsoft.playwright.Locator;
    import com.microsoft.playwright.Page;
    import locators.XeroxLogin;
    import locators.XeroxRPDLocators;
    import org.testng.annotations.Test;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.Properties;
    import java.util.Random;

    public class XeroxRPD extends BaseTest
    {
        static Random r1 = new Random();
        static int RandomNum = r1.nextInt(1000);
        public static String MSG1 = "Send L1 RPD Xerox theme " + RandomNum;
        public static String MSG2 = "Send L2 RPD Xerox theme " + RandomNum;
        public static String MSG3 = "Send L3 RPD Xerox theme " + RandomNum;
        public Page tab1;
        public Page tab2;
        public Page tab3;

        @Test
        public void xerox() throws Exception
        {
            page.setDefaultTimeout(150000);
            Properties prop = propertiesCode();
            ReadersPage rp = new ReadersPage();
            page.navigate(prop.getProperty("xerox_url"));
            Page popup = page.waitForPopup(() -> page.click("(//span[@class='btn-label'])[1]"));
            popup.waitForLoadState();
            popup.fill(XeroxLogin.username, prop.getProperty("xerox_username"));
            popup.click(XeroxLogin.next);
            popup.fill(XeroxLogin.password, prop.getProperty("xerox_password"));
            popup.click(XeroxLogin.submit);
            for (int i = 1; i <= 3; i++)
            {
                page.bringToFront();
                page.locator(XeroxLogin.protect).click();
                page.locator(XeroxLogin.dropdown).click();
                Page page1 = page.waitForPopup(() -> page.locator(XeroxLogin.box).click());
                page1.waitForLoadState();
                page1.fill(XeroxLogin.boxUsername, prop.getProperty("xerox_username"));
                page1.fill(XeroxLogin.boxPassword, prop.getProperty("xerox_password"));
                page1.click(XeroxLogin.boxLogin);
                page1.click(XeroxLogin.boxAccess);
                page.click(XeroxLogin.folder);
                page.click(XeroxLogin.file);
                page.click(XeroxLogin.boxOK);
                page.locator(XeroxLogin.next1).click();
                if (i == 2) {
                    page.locator(XeroxLogin.trackViewers).click();
                } else if (i == 3) {
                    page.locator(XeroxLogin.restrictViewers).click();
                } else
                    System.out.println("Selected Track Viewers");
                if (i == 1)
                    page.locator(XeroxLogin.includeNote).fill(XeroxRPD.MSG1);
                else if (i == 2)
                    page.locator(XeroxLogin.includeNote).fill(XeroxRPD.MSG2);
                else
                    page.locator(XeroxLogin.includeNote).fill(XeroxRPD.MSG3);
                if (i == 1) {
                    page.locator(XeroxLogin.timestamp).click();
                    page.locator(XeroxLogin.print).click();
                } else {
                    page.locator(XeroxLogin.identifyLeakers).click();
                    page.locator(XeroxLogin.timestamp).click();
                    page.locator(XeroxLogin.print).click();
                }
                page.locator(XeroxLogin.userList).click();
                page.locator(XeroxLogin.email).fill(prop.getProperty("reader_mailinator"));
                page.locator(XeroxLogin.add).click();
                page.locator(XeroxLogin.emailDocument).click();
                if (i == 1) {
                    page.locator(XeroxLogin.subject).fill(XeroxRPD.MSG1);
                    page.locator(XeroxLogin.message).fill(XeroxRPD.MSG1);
                } else if (i == 2) {
                    page.locator(XeroxLogin.subject).fill(XeroxRPD.MSG2);
                    page.locator(XeroxLogin.message).fill(XeroxRPD.MSG2);
                } else {
                    page.locator(XeroxLogin.subject).fill(XeroxRPD.MSG3);
                    page.locator(XeroxLogin.message).fill(XeroxRPD.MSG3);
                }
                page.locator(XeroxLogin.next2).click();

                if (i == 1) {
                    Download download = page.waitForDownload(() -> page.click(XeroxLogin.run));
                    Path downloadPath = Paths.get("C:\\Users\\rpl02\\OneDrive\\Documents\\Xerox RPDs\\Xerox L1 " + RandomNum + ".html");
                    download.saveAs(downloadPath);
                    String filePath = "file:///" + downloadPath.toAbsolutePath().toString().replace("\\", "/");
                    tab1 = context.newPage();
                    for (int j = 1; j <= 8; j++)
                    {
                        tab1.click(XeroxRPDLocators.dropdown);
                        tab1.locator("(//button[@class='RobotoFont dropdown-item'])[" + i + "]").click();
                    }
                    tab1.click(XeroxRPDLocators.SideNote);
                    String s=tab1.locator(XeroxRPDLocators.SideNote1).textContent();
                    System.out.println(s);
                    tab1.click(XeroxRPDLocators.ReplyToDocument);
                    tab1.fill(XeroxRPDLocators.Subject, XeroxRPD.MSG1);
                    tab1.fill(XeroxRPDLocators.Message, XeroxRPD.MSG1);
                    tab1.click(XeroxRPDLocators.Send);
                    tab1.click(XeroxRPDLocators.close);
                }
                else if (i == 2) {
                    Download download = page.waitForDownload(() -> page.click(XeroxLogin.run));
                    Path downloadPath = Paths.get("C:\\Users\\rpl02\\OneDrive\\Documents\\Xerox RPDs\\Xerox L2 " + RandomNum + ".html");
                    download.saveAs(downloadPath);
                    String filePath = "file:///" + downloadPath.toAbsolutePath().toString().replace("\\", "/");
                    tab2 = context.newPage();
                    tab2.navigate(filePath);
                    tab2.click("//p[text()='Get Passcode']");
                    Locator emailField = tab2.locator("//*[@id='email']");
                    emailField.click();
                    emailField.type(prop.getProperty("reader_mailinator"), new Locator.TypeOptions().setDelay(100));
                    tab2.click("//*[text()='OK']");
                    tab2.click("//*[text()='OK']");
                    Page mailinator = context.newPage();
                    mailinator.navigate("https://www.mailinator.com");
                    page.bringToFront();
                    mailinator.fill("//*[@id='search']", prop.getProperty("reader_mailinator"));
                    Thread.sleep(2000);
                    mailinator.click("//*[text()='GO']");
                    mailinator.click("(//*[@class='ng-binding'])[1]");
                    String passcode = mailinator.frameLocator("//*[@id='html_msg_body']").locator("//span[@class='font-10']").textContent();
                    page.bringToFront();
                    tab2.fill("//*[@class='RobotoFont form-control input_pass']", passcode);
                    tab2.click("//*[text()='OK']");
                    for (int j = 1; j <= 8; j++)
                    {
                        tab2.click(XeroxRPDLocators.dropdown);
                        tab2.locator("(//button[@class='RobotoFont dropdown-item'])[" + i + "]").click();
                    }
                    String s=tab2.locator(XeroxRPDLocators.SideNote1).textContent();
                    System.out.println(s);
                    tab2.click(XeroxRPDLocators.ReplyToDocument);
                    tab2.fill(XeroxRPDLocators.Subject, XeroxRPD.MSG2);
                    tab2.fill(XeroxRPDLocators.Message, XeroxRPD.MSG2);
                    tab2.click(XeroxRPDLocators.Send);
                    tab2.click(XeroxRPDLocators.close);
                }
                else {
                    Download download = page.waitForDownload(() -> page.click(XeroxLogin.run));
                    Path downloadPath = Paths.get("C:\\Users\\rpl02\\OneDrive\\Documents\\Xerox RPDs\\Xerox L3 " + RandomNum + ".html");
                    download.saveAs(downloadPath);
                    String filePath = "file:///" + downloadPath.toAbsolutePath().toString().replace("\\", "/");
                    tab3 = context.newPage();
                    tab3.navigate(filePath);
                    tab3.click("//p[text()='Get Passcode']");
                    Locator emailField = tab3.locator("//*[@id='email']");
                    emailField.click();
                    emailField.type(prop.getProperty("reader_mailinator"), new Locator.TypeOptions().setDelay(100));
                    tab3.click("//*[text()='OK']");
                    tab3.click("//*[text()='OK']");
                    Page mailinator = context.newPage();
                    page.bringToFront();
                    mailinator.navigate("https://www.mailinator.com");
                    page.bringToFront();
                    mailinator.fill("//*[@id='search']", prop.getProperty("reader_mailinator"));
                    Thread.sleep(2000);
                    mailinator.click("//*[text()='GO']");
                    mailinator.click("(//*[@class='ng-binding'])[1]");
                    String passcode = mailinator.frameLocator("//*[@id='html_msg_body']").locator("//span[@class='font-10']").textContent();
                    page.bringToFront();
                    tab3.fill("//*[@class='RobotoFont form-control input_pass']", passcode);
                    tab3.click("//*[text()='OK']");
                    for (int j = 1; j <= 8; j++)
                    {
                        tab3.click(XeroxRPDLocators.dropdown);
                        tab3.locator("(//button[@class='RobotoFont dropdown-item'])[" + i + "]").click();
                    }
                    tab3.click(XeroxRPDLocators.SideNote);
                    String s=tab3.locator(XeroxRPDLocators.SideNote1).textContent();
                    System.out.println(s);
                    tab3.click(XeroxRPDLocators.ReplyToDocument);
                    tab3.fill(XeroxRPDLocators.Subject, XeroxRPD.MSG3);
                    tab3.fill(XeroxRPDLocators.Message, XeroxRPD.MSG3);
                    tab3.click(XeroxRPDLocators.Send);
                    tab3.click(XeroxRPDLocators.close);
                }

            }

        }
    }
