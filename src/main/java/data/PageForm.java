package data;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.net.HttpCookie;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PageForm {
    private static HttpCookie month;
    private static String appURL;
    private static String appGate;
    private static HttpCookie cardNumber;
    private static HttpCookie year;
    private static HttpCookie cardOwner;
    private static HttpCookie cvcOrCvvNumber;

    public static void main(String[] args) {
        open("http://localhost:9999");


        SelenideElement cardNumberField = $(By.name("card_number"));
        SelenideElement expiryMonthField = $(By.name("expiry_month"));
        SelenideElement expiryYearField = $(By.name("expiry_year"));
        SelenideElement cardHolderField = $(By.name("card_holder"));
        SelenideElement cvcField = $(By.name("cvc"));

        $("button[type='submit']").click();
    }

    public static void byuOnMoney() {
        open(appURL + ":" + appGate);
        $$(".button_content").find(exactText("Купить")).click();
        $$(".theme_alfa").find(exactText("Оплата по карте")).shouldBe(visible);

    }

    public static void byuOnCredit() {
        open(appURL + ":" + appGate);
        $$(".button_content").find(exactText("Купить в кредит")).click();
        $$(".theme_alfa").find(exactText("Кредит по данным карты")).shouldBe(visible);

    }

    public static void cardWrongFormat() {
        $$(".input__sub").find(exactText("Неверный формат")).shouldBe(visible);
    }


    public static void dateWrongFormat() {
        $$(".input__sub").find(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public static void cardExpiration() {
        $$(".input__sub").find(exactText("Истек срок действия карты")).shouldBe(visible);

    }

    public static void successfulPageFilling() {
        $(Selectors.withText("Операция одобрена банком")).shouldBe(visible);
    }


    public static void unSuccessfulPageFilling() {
        $(Selectors.withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void continuePageFilling() {
        $$(".button_content").find(exactText("Продолжить")).click();
    }

    public static void setCardNumber(String cNumber) {
        cardNumber.setValue(cNumber);
    }

    public static void setCardMonth(String cMonth) {
        month.setValue(cMonth);
    }

    public static void setCardYear(String cYear) {
        year.setValue(cYear);
    }

    public static void setCardOwner(String cOwner) {
        cardOwner.setValue(cOwner);
    }

    public static void setCardCVV(String cCvv) {
        cvcOrCvvNumber.setValue(cCvv);
    }

    public static void pushContinueButton() {
        $$(".button__content").find(exactText("Продолжить")).click();
    }
}
