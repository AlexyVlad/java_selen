package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    SelenideElement
    loginField = $(byName("username")),
    passwordField = $(byName("password")),
    loginButton = $(byText("Войти через"));

    public void login (String userName, String password) {
        //авторизация в mail
        open("https://account.mail.ru/login?opener=o2&x=&page=https%3A%2F%2Fo2.mail.ru%2Fxlogin%3Fclient_id%3Dbbddb88d19b84a62aedd1ffbc71af201%26response_type%3Dcode%26scope%3D%26redirect_uri%3Dhttps%253A%252F%252Fauth-ac.my.games%252Fsocial%252Fmailru_callback%252F%26state%3D8716816a385ad456c91f1344d0e723f1%26no_biz%3D1%26force_us%3D1%26signup_target%3D_self%26remind_target%3D_self%26logo_target%3D_none&email=&logo_target=_none&signup_target=_self&remind_target=_self&cancel_page=https%3A%2F%2Fo2.mail.ru%2Flogin%3Fclient_id%3Dbbddb88d19b84a62aedd1ffbc71af201%26response_type%3Dcode%26scope%3D%26redirect_uri%3Dhttps%253A%252F%252Fauth-ac.my.games%252Fsocial%252Fmailru_callback%252F%26state%3D8716816a385ad456c91f1344d0e723f1%26no_biz%3D1%26fail%3D1");
        this.loginField.sendKeys(userName);
        this.loginField.pressEnter();
        this.passwordField.sendKeys(password);
        this.passwordField.pressEnter();
        this.loginButton.click();

        //авторизация в двар
        switchTo().window(1);
        $(byText(userName)).click();
        switchTo().window(0);
        open("http://w1.dwar.ru/main.php");
        $(byId("form_btn_enter")).click();
        switchTo().window(1);
        $(byText("Продолжить")).click();
        switchTo().window(0);
        $(byId("form_btn_go")).click();
    }
}
