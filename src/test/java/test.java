import org.testng.annotations.Test;
import pages.BirjaPage;
import pages.LoginPage;
import pages.NumberLots;
import pages.Сombination;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

public class test {

       @Test
       public void testik() {
        LoginPage loginPage = new LoginPage();
        BirjaPage birjaPage = new BirjaPage();
        Сombination combination = new Сombination();
        NumberLots numberLots = new NumberLots();

           HashMap<String, Integer> quantity = new HashMap<>();
           HashMap<String, Integer> durability_1 = new HashMap<>();
           HashMap<String, Integer> durability_2 = new HashMap<>();
           HashMap<String, Integer> maxPrice = new HashMap<>();
           HashMap<String, Integer> minPrice = new HashMap<>();

//наш список закупок
           quantity.put("Малый каркасный колчан", 1);
           durability_1.put("Малый каркасный колчан", 1);
           durability_2.put("Малый каркасный колчан", 7);
           maxPrice.put("Малый каркасный колчан", 12500);
           minPrice.put("Малый каркасный колчан", 4000);

           quantity.put("Багряная стерлядь", 0);
           durability_1.put("Багряная стерлядь", 0);
           durability_2.put("Багряная стерлядь", 0);
           maxPrice.put("Багряная стерлядь", 300);
           minPrice.put("Багряная стерлядь", 100);

           quantity.put("Сумеречный Дайто", 1);
           durability_1.put("Сумеречный Дайто", 1);
           durability_2.put("Сумеречный Дайто", 65);
           maxPrice.put("Сумеречный Дайто", 47500);
           minPrice.put("Сумеречный Дайто", 25000);

           quantity.put("Раритетная кость", 0);
           durability_1.put("Раритетная кость", 0);
           durability_2.put("Раритетная кость", 0);
           maxPrice.put("Раритетная кость", 300 );
           minPrice.put("Раритетная кость", 100);
//конец списка

        loginPage.login("email", "pass"); // наши данные подставить тут
        refresh();
        birjaPage.clickAyk();
        String [] goods = {"Сумеречный Дайто"};
           numberLots.scanInitialQuantity(goods);
           while (true) {
               for (int i = 0; i < goods.length; i++) {
                   combination.Home(goods[i], quantity.get(goods[i]), durability_1.get(goods[i]),
                           durability_2.get(goods[i]), maxPrice.get(goods[i]), minPrice.get(goods[i]), goods);
               }
               numberLots.checkNumberOfLots();
               sleep(600000);
               //sleep(600000);
               numberLots.comparisonNumberLots(goods);
           }
       }
}


