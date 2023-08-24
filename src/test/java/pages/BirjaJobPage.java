package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BirjaJobPage {

    SelenideElement
            newLot = $(byXpath("//a[@href='area_auction.php?&mode=newrequest']")), //Новая заявка
            lot = $(byXpath("//td//span[@class='artikul-container']")), //иконка лота
            gold = $(byId("cost3")),
            silver =  $(byId("cost2")),
            bronze = $(byId("cost1")),
            howMuch = $(byXpath("//input[@name='form[n]']")),
            howMuchInactive = $(byXpath("//input[@name='form[n]' and @disabled]")),
            durabilityMin = $(byXpath("//input[@name='form[durability]']")),
            durabilityMax = $(byXpath("//input[@name='form[durability_max]']")),
            buttonSetLot = $(byXpath("//input[@value='Выставить']"));

    public void putUpLot (int quantity, int price, int durability_1, int durability_2) {
        BirjaPage birjaPage = new BirjaPage();

        price++;
        String inputGold = Integer.toString(price / 10000);
        String inputBronze = Integer.toString(price % 100);
        String inputSilver = Integer.toString(price / 100 % 100);
        System.out.println("Ставим новый лот по цене "+ price + " медных" + ", в количестве " + quantity + " шт");
        birjaPage.customClick(newLot);
        birjaPage.customClick(lot);
        bronze.clear();
        birjaPage.customSleep();
        silver.clear();
        birjaPage.customSleep();
        gold.clear();
        birjaPage.customSleep();
        gold.sendKeys(inputGold);
        birjaPage.customSleep();
        silver.sendKeys(inputSilver);
        birjaPage.customSleep();
        bronze.sendKeys(inputBronze);
        birjaPage.customSleep();
          if (!howMuchInactive.exists()) howMuch.setValue(String.valueOf(quantity));
          if (durability_1 > 0 || durability_2 > 0) {
              durabilityMin.clear();
              birjaPage.customSleep();
              durabilityMin.sendKeys(String.valueOf(durability_1));
              birjaPage.customSleep();
              durabilityMax.clear();
              birjaPage.customSleep();
              durabilityMax.sendKeys(String.valueOf(durability_2));
        }
        birjaPage.customClick(buttonSetLot);
        Сombination.countLots++;
        System.out.println("Выставили лототов_"+ Сombination.countLots);
    }

    SelenideElement
            myApplications = $(byXpath("//a[@href='area_auction.php?&mode=myrequest']")), //мои заявки
            okButton = $(byXpath("//input[@value='Ok']"));

    public void overridingUpLot (int price, String product, int quantity, int durability_1, int durability_2, String [] goods) {
        BirjaPage birjaPage = new BirjaPage();
        PricePage pricePage = new PricePage();
        NumberLots numberLots = new NumberLots();
        birjaPage.customSleep();
        birjaPage.customClick(myApplications);
        pricePage.cancelingLot(product);
        birjaPage.customSleep();
        Selenide.switchTo().defaultContent();
        birjaPage.customClick(okButton);
        birjaPage.checkPost();
        birjaPage.customSleep();
        numberLots.scanChangeQuantity(goods);
        birjaPage.customSleep();
        quantity = quantity-NumberLots.changeQuantity.get(product)+NumberLots.zeroQuantity.get(product);
        putUpLot(quantity, price, durability_1, durability_2);
    }
}
