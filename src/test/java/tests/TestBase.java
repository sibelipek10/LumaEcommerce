package tests;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.Page;

import base.PlaywrightFactory;
import pages.HomePage;
import utils.ExtentReporter;
import utils.TestProperties;


public class TestBase {

    protected Page page;
    protected SoftAssert softAssert = new SoftAssert();
    protected HomePage homePage;
    protected static ExtentReports reporter;
    protected static TestProperties testProperties;
    private static Logger log;
    private String emailtextbox="#email";
    private String passwordtextbox="(//input[@id='pass'])[1]";
    private String signin="div[class='panel header'] li[data-label='or'] a";
    private String signin1="fieldset[class='fieldset login'] div[class='primary'] span";
    private String loginAssert="div[class='panel header'] span[class='logged-in']";




    /**
     * BeforeSuite method to clean up the test-results directory and initialize the
     * extent reporter, logger and read test properties
     *
     * @throws Exception
     */
    @BeforeSuite
    public void setupBeforeTestSuite() throws Exception {
        File file = new File("test-results");
        if (file.exists() && !deleteDirectory(file)) {
            throw new Exception("Exception occurred while deleting test-results directory");
        }
        log = LogManager.getLogger();
        testProperties = new TestProperties();
        testProperties.updateTestProperties();
        reporter = ExtentReporter.getExtentReporter(testProperties);
    }

    /**
     * AfterSuite method to assert all the soft assertions and flush(write) the
     * extent report
     */
    @AfterSuite
    public void teardownAfterTestSuite() {
        try {
            softAssert.assertAll();
            reporter.flush();
        } catch (Exception e) {
            log.error("Error in AfterSuite Method ", e);
        }
    }

    @BeforeMethod
    public void startPlaywrightServer() {
        PlaywrightFactory pf = new PlaywrightFactory(testProperties);
        page = pf.createPage();
        page.navigate(testProperties.getProperty("url"), new Page.NavigateOptions().setTimeout(120000));
    }


    @AfterMethod
    public void closePage(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {

            String path = System.getProperty("user.dir") + "/test-results/screenshots/" + System.currentTimeMillis()
                    + ".png";

            byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
            String base64Path = Base64.getEncoder().encodeToString(buffer);

            log.debug("Screenshot is taken and saved at the location  {}", path);


            try (FileInputStream inputStream = new FileInputStream(path)) {
                String attachmentName = "Screenshot_" + result.getName() + ".png"; // Unique name for each screenshot
                Allure.addAttachment(attachmentName, inputStream);
            }

        }

        page.context().browser().close();
    }
    public void login() throws InterruptedException {
        page.click(signin);
        page.mouse().wheel(0,300);
        page.fill(emailtextbox,testProperties.getProperty("userDataMail"));
        page.click(passwordtextbox);
        Thread.sleep(1000);
        page.fill(passwordtextbox,testProperties.getProperty("password"));
        page.click(signin1);
        Assert.assertEquals(page.textContent(loginAssert),"Welcome, sibel polat!","hata");

    }
    /**
     * Method to delete the directory recursively
     *
     * @param directoryToBeDeleted - {@link File} to be deleted
     * @return boolean - Returns {@link Boolean} of delete operation
     */
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    }





