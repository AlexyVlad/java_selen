package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Сombination {
   static int countLots = 0;
   static int  countСheckLots = 0;
   SelenideElement
           myApplications = $(byXpath("//a[@href='area_auction.php?&mode=myrequest']")), //мои заявки
           myApplicationsPressed = $(byXpath("//a[@href='area_auction.php?&mode=myrequest' and @class = 'tbl-fgt_tabs-link_act']")),
           exchange = $(byXpath("//a[@href='area_auction.php?&mode=request']")), //биржа
           searchString = $(byName("_filter[title]")), //строка поиска в бирже
           checkbox = $(byXpath("//input[@name='_filter[ihave]' and @type='checkbox']"));
   String myName = "Гарошик[3]"; //наш ник

   public void Home(String product, int quantity, int durability_1, int durability_2, int maxPrice, int minPrice, String [] goods) {

       int dynamicQuantity = quantity-NumberLots.changeQuantity.get(product)+NumberLots.zeroQuantity.get(product);

      PricePage pricePage = new PricePage();
      BirjaPage birjaPage = new BirjaPage();
      BirjaJobPage birjaJobPage = new BirjaJobPage();

      if (dynamicQuantity <= 0) return;
      birjaPage.customSleep();
      if (!myApplicationsPressed.exists()) birjaPage.customClick(myApplications);
      if (pricePage.getMyLots(product)) {   //лот есть
         birjaPage.customClick(exchange);
         if (checkbox.isSelected()) birjaPage.customClick(checkbox);
               printNumberOfChecks();
         birjaPage.customSleep();
         searchString.clear();
         birjaPage.customSleep();
         searchString.sendKeys(product);
         birjaPage.customSleep();
         searchString.pressEnter();
         birjaPage.customSleep();

         if (!birjaPage.getName(pricePage.getPriceID(product)).equals(myName))  //первые ли мы в списке?
         {
            int price = pricePage.getBestPrice(minPrice, product);
            if (price < maxPrice)
               birjaJobPage.overridingUpLot(price, product, dynamicQuantity, durability_1, durability_2, goods); //перебиваем на 1м
         }
      } else {
         birjaPage.customClick(exchange);  //если лота нет, смотрим цену и ставим
         if (checkbox.isSelected()) birjaPage.customClick(checkbox);
         printNumberOfChecks();
          searchString.clear();
         birjaPage.customSleep();
         searchString.sendKeys(product);
         birjaPage.customSleep();
         searchString.pressEnter();
         int price = pricePage.getBestPrice(minPrice, product);
         if (price < maxPrice) birjaJobPage.putUpLot(dynamicQuantity, price, durability_1, durability_2); //ставим лот
      }
   }
   public void printNumberOfChecks () {
      countСheckLots++;
      System.out.println("Прошло проверок лотов_" + countСheckLots);
   }
}
