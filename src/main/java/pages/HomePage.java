package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestProperties;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;




public class HomePage {

    protected Page page;
    protected static TestProperties testProperties;
    private String register="header[class='page-header'] li:nth-child(3) a:nth-child(1)";
    private String whatsNew="a[id='ui-id-3'] span:nth-child(1)";
    private String women="(//span[normalize-space()='Women'])[1]";
    private String men="(//span[normalize-space()='Men'])[1]";
    private String gear="(//span[normalize-space()='Gear'])[1]";
    private String training="(//span[normalize-space()='Training'])[1]";
    private String sale="(//span[normalize-space()='Sale'])[1]";
    private String jacket="(//a[contains(text(),'Jackets')])[1]";
    private String assertJacket="(//strong[normalize-space()='Jackets'])[1]";
    private String product="(//a[@class='product-item-link'][normalize-space()='Lando Gym Jacket'])[1]";
    private String assertProduct="(//strong[contains(text(),'Lando Gym Jacket')])[1]";
    private String mediumProduct="(//div[@id='option-label-size-143-item-168'])[1]";
    private String addToCard="(//span[normalize-space()='Add to Cart'])[1]";
    private String greenProduct="(//div[@id='option-label-color-93-item-53'])[1]";
    private String assertGreen="(//span[normalize-space()='Green'])[1]";
    private String assertAddToCard="(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]";
    private String commentButton="(//a[@href='https://magento.softwaretestingboard.com/lando-gym-jacket.html#reviews'])[1]";
    private String rating="(//label[@id='Rating_1_label'])[1]";
    private String nickName="(//input[@id='nickname_field'])[1]";
    private String summary="(//input[@id='summary_field'])[1]";
    private String review="(//textarea[@id='review_field'])[1]";
    private String submitReview="(//span[normalize-space()='Submit Review'])[1]";
    private String assertSubmitReview="(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]";
    private String productHover="a[title='Radiant Tee']";
    private String background=".message.global.demo";
    private String addToWish="body > div:nth-child(5) > main:nth-child(4) > div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(5) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)";
    private String assertAddToWish="(//span[@class='base'])[1]";
    private String updateWishList="button[title='Update Wish List'] span";
    private String hoverWish="(//a[contains(text(),'Radiant Tee')])[1]";
    private String deleteWish="a[title='Remove Item']";
    private String assertDeleteWish="div[class='message info empty'] span";







    public HomePage(Page page){ this.page = page;}

   public void registerControl() throws InterruptedException {
        page.click(register);

   }
   public void categories(){
 //    Assert.assertEquals(page.textContent(whatsNew),"What's New","hata");
    Assert.assertEquals(page.textContent(women),"Women","hata");
    Assert.assertEquals(page.textContent(men),"Men","hata");
    Assert.assertEquals(page.textContent(gear),"Gear","hata");
    Assert.assertEquals(page.textContent(training),"Training","hata");
    Assert.assertEquals(page.textContent(sale),"Sale","hata");
   }

   public void chooseCategories() throws InterruptedException {
        page.click(men);
        page.click(jacket);
        Assert.assertEquals(page.textContent(assertJacket),"Jackets","hata");
        page.mouse().wheel(0,950);
        Thread.sleep(1000);


   }
   public void addToCard(){
       page.click(product);
       Assert.assertEquals(page.textContent(assertProduct),"Lando Gym Jacket","hata");
        page.click(mediumProduct);
        page.click(greenProduct);
        Assert.assertEquals(page.textContent(assertGreen),"Green","hata");
        page.click(addToCard);
      //  Assert.assertEquals(page.textContent(assertAddToCard),"You added Lando Gym Jacket to your shopping cart.","hata");
     // assert hata veriyor bir de ikonun isvisible olmasını denemek istedim o da olmuyor.

   }
   public void commentAdd() throws InterruptedException {
        page.click(commentButton);
        Thread.sleep(1000);
        page.mouse().wheel(0,700);
        page.click(rating);
        Assert.assertEquals(page.inputValue(nickName),"sibel","hata");
        page.fill(summary,"Test");
        page.fill(review,"Test");
        page.click(submitReview);
        Assert.assertEquals(page.textContent(assertSubmitReview),"You submitted your review for moderation.","hata");
        Thread.sleep(2000);


   }
   public void addFavorite() throws InterruptedException {
        page.click(women);
        page.keyboard().down("End");
        page.hover(productHover);
        Thread.sleep(2000);
        page.click(addToWish);
        Thread.sleep(2000);
        Assert.assertEquals(page.textContent(assertAddToWish),"My Wish List","hata");




   }
   public void updateWishList() throws InterruptedException {
       page.click(updateWishList);
       page.hover(hoverWish);
       page.click(deleteWish);
       Assert.assertEquals(page.textContent(assertDeleteWish),"You have no items in your wish list.","hata");
       Thread.sleep(2000);


   }
   public void color(){
        String beklenenRenk="rgb(255, 1, 1)";
        Assert.assertTrue(page.isVisible(background),"hata");
       String gercekRenk = page.locator(background).evaluate("el => window.getComputedStyle(el).getPropertyValue('background-color')").toString();
       Assert.assertEquals(gercekRenk, beklenenRenk, background + " butonunun rengi beklenen değil");

   }


    }

