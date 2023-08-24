package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PricePage {
    public boolean getMyLots(String product) { //Поиск нужного нам лота
        List<String> lots = $$(byXpath("//a[@class='b']")).texts();
        return lots.contains(product);
    }

    public void cancelingLot(String product) { //Клик по отмене лота
        BirjaPage birjaPage = new BirjaPage();
        birjaPage.customClick($(byXpath(String.format("//tr[./td[./a[@class='b' and text()='%s']]]//input[@value='отменить']", product))));
        System.out.println("Сняли "+ product);
    }

    public int getBestPrice(int Min_Price, String product) {
        if ($(byXpath("//tr[@height='17']//div//span[@class='mgold']")).exists()) {
            ElementsCollection
                    goods = $$(byXpath(String.format("//tr[@height='17'][.//a[contains(text(), '%s')]]", product)));
            int[] price= new int[goods.size()];
            for (int i = 0; i < goods.size(); i++) {
                price[i] = getPrice(goods.get(i));
            }
            Arrays.sort(price);
            return price[price.length-1];
        } else return Min_Price - 1;
    }

    public int getPriceID(String product) {
        int temp = 0;
        int max = -1;
        if ($(byXpath("//tr[@height='17']//div//span[@class='mgold']")).exists()) {
            ElementsCollection
                    goods = $$(byXpath(String.format("//tr[@height='17'][.//a[contains(text(), '%s')]]", product)));
            int[] price = new int[goods.size()];
            for (int i = 0; i < goods.size(); i++) {
                price[i] = getPrice(goods.get(i));
            }
            for (int i = 0; i < price.length; i++) {
                if (max < price[i]) {
                    max = price[i];
                    temp = i;
                }
            }
        }  return temp;
    }

    public int getPrice (SelenideElement elem) {
        String stringPrice = String.format("%s%s%s", okrygletel(elem.$(".mgold").getText()), okrygletel(elem.$(".msilver").getText()), okrygletel(elem.$(".mbronze").getText())).replaceAll(" ", "");
        return Integer.parseInt(stringPrice);
    }

    public String okrygletel (String str) {
        if (str.replaceAll(" ", "").length() < 2) return "0"+str;
        else return str;
    }
}
