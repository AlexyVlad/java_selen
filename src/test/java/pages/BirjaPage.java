package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BirjaPage {
    public String getName (int id) {
      ElementsCollection
         buyersNames = $$(byXpath("//tr[@height='17'][.//b]//td[@class='p6h'][2]")); //Имя перса
        return buyersNames.get(id).getText().replaceAll(" ", "");
    }
      public void clickAyk () {
        Selenide.switchTo().defaultContent();
          Selenide.switchTo().frame($("#main_frame"));
          $(byXpath("//canvas[@width='670']")).should(Condition.exist);
          Selenide.actions().moveToElement($(byXpath("//canvas[@width='670']")), (int) (Math.random() * 3)+142, (int) (Math.random() * 2)+1).click().build().perform();
          Selenide.switchTo().frame($("#main"));
          customSleep();
      }

      public void clickPost () {
        SelenideElement
          postButton = $(byXpath("//canvas[@width='104']"));
          Selenide.switchTo().defaultContent();
          Selenide.switchTo().frame($("#main_frame"));
          Selenide.actions().moveToElement(postButton, (int) (Math.random() * 3)+1, (int) (Math.random() * 3)-143).click().build().perform();
          Selenide.switchTo().frame($("#main"));
          $(byXpath("//a[@href='area_post.php?&mode=inbox']")).should(Condition.exist);
      }

      public void customClick (SelenideElement elem) {
        Selenide.actions().moveToElement (elem, (int) (Math.random() * 3) + 1, (int) (Math.random() * 2) + 1).click().build().perform();
        sleep(Math.round(140+ Math.random()*1100));
      }

      public void customSleep () { sleep(Math.round(120+ Math.random()*1100)); }

      public void pickUpPost () {
        SelenideElement
                takeEverything =  $(byXpath("//label[@for='_chk_0']")),
                takeValues = $(byXpath("//input[@value='Забрать и удалить']"));
          customClick(takeEverything);
          customClick(takeValues);
      }

      public void checkPost () {
        clickPost();
         if ($(byXpath("//label[@for='_chk_0']")).exists()) pickUpPost();
             else clickAyk();
        clickAyk();
      }
}