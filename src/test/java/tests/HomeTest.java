package tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestProperties;

/**
 * Unit test for simple App.
 */
public class HomeTest extends TestBase
{
    @BeforeMethod
    public void beforeMethod(){
        homePage = new HomePage(page);
    }
    private String nameTextbox="#firstname";
    private String lastNameTextbox="#lastname";
    private String eMail="#email_address";
    private String password="#password";
    private String confirmPassword="#password-confirmation";
    private String createButton="button[title='Create an Account'] span";
    private String mailAssert="div[data-bind='html: $parent.prepareMessageForHtml(message.text)']";

    @Test
    public void navigateOnline() throws InterruptedException {
        Thread.sleep(1000);

    }
    @Test
    public void logincontrol() throws InterruptedException {

        login();
        Thread.sleep(2000);

    }
    @Test
    public void sameRegisterControl() throws InterruptedException {

        homePage.registerControl();
        page.mouse().wheel(0,450);
        page.click(nameTextbox);
        Thread.sleep(2000);
        page.fill(nameTextbox,testProperties.getProperty("userName"));
        Thread.sleep(2000);
        page.fill(lastNameTextbox,testProperties.getProperty("userLastName"));
        page.fill(eMail,testProperties.getProperty("userDataMail"));
        page.fill(password,testProperties.getProperty("password"));
        page.fill(confirmPassword,testProperties.getProperty("password"));
        page.click(createButton);
        Assert.assertTrue(page.isVisible(mailAssert),"ikon görünür değil");
        Thread.sleep(2000);
    }
    @Test
    public void categoriesControl() throws InterruptedException {

        homePage.categories();

    }
    @Test
    public void chooseCategoriesControl() throws InterruptedException {

        homePage.chooseCategories();
        Thread.sleep(1000);
    }
    @Test
    public void addToCardControl() throws InterruptedException {

        login();
        homePage.chooseCategories();
        homePage.addToCard();
    }
    @Test
    public void commentAddControl() throws InterruptedException {

        login();
        homePage.chooseCategories();
        homePage.commentAdd();
    }
    @Test
    public void colorControl(){

        homePage.color();
    }
    @Test
    public void addFavoriteControl() throws InterruptedException {

        login();
        homePage.addFavorite();
    }
    @Test
    public void updateWishListControl() throws InterruptedException {

        login();
        homePage.addFavorite();
        homePage.updateWishList();


    }

}
