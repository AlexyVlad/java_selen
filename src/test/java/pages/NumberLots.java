package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NumberLots {
    public static int numberOfLots;
    public static final HashMap<String, Integer> zeroQuantity = new HashMap<>();
    public static final HashMap<String, Integer> changeQuantity = new HashMap<>();

    public int changeInQuantity (String product) {

        SelenideElement
               thing = $(byXpath(String.format("//table[@data-title='%s']//td", product)));

        if (!thing.exists()) return 0;

        int numberOfItems = Integer.parseInt(thing.getAttribute("cnt"));

        ElementsCollection
                things = $$(byXpath(String.format("//table[@data-title='%s']//td", product)));

        if (!thing.exists()) return 0;
            if (numberOfItems > 0)
                return numberOfItems;
            else
                return things.size();
    }

     private void clickBackpack() {
        BirjaPage birjaPage = new BirjaPage();
         SelenideElement
                 backpackButton =  $(byXpath("//a[@href='area_auction.php?&mode=newlot&group=0']")),
                 newLotButton = $(byXpath("//a[@href='area_auction.php?&mode=newlot']"));
        birjaPage.customClick(newLotButton);
        birjaPage.customClick(backpackButton);
    }

    public void scanInitialQuantity (String[] products) {
        NumberLots numberLots = new NumberLots();
        numberLots.clickBackpack();
        for (int i = 0; i < products.length; i++) {
            zeroQuantity.put(products[i], changeInQuantity(products[i]));
        }
        changeQuantity.putAll(zeroQuantity);
    }

    public void scanChangeQuantity (String[] products) {
        NumberLots numberLots = new NumberLots();
        numberLots.clickBackpack();
        for (int i = 0; i < products.length; i++)
            changeQuantity.put(products[i], changeInQuantity(products[i]));
        System.out.println(zeroQuantity);
        System.out.println(changeQuantity);
    }

    BirjaPage birjaPage = new BirjaPage();

    ElementsCollection
            Lots = $$(byXpath("//a[@class='b']"));

    SelenideElement
            myApplications = $(byXpath("//a[@href='area_auction.php?&mode=myrequest']"));
    public void checkNumberOfLots () {
        birjaPage.customClick(myApplications);
        numberOfLots = Lots.size(); //выдергиваем количество лотов до ожидания
    }

    public void comparisonNumberLots (String[] products) { //сравниваем количество до ожидания и после
        birjaPage.customClick(myApplications);
               if (numberOfLots != Lots.size()) {
                   birjaPage.checkPost();
                   birjaPage.clickAyk();
                   scanChangeQuantity(products);
               }
    }
}
