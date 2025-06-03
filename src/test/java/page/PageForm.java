package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PageForm {
    public PageForm(String baseUrl) {
        Selenide.open(baseUrl);
    }

    static List<SelenideElement> input = Selenide.$$(".input__control");
    static SelenideElement cardNumber = input.get(0);
    static SelenideElement month = input.get(1);
    static SelenideElement year = input.get(2);
    static SelenideElement cardOwner = input.get(3);
    static SelenideElement cvcOrCvvNumber = input.get(4);

    public static void buyOnMoney() {
        $$(".button_content").find(exactText("Купить")).click();
        $$(".theme_alfa").find(exactText("Оплата по карте")).shouldBe(visible);

    }

    public static void buyOnCredit() {
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

    public void checkCVVInputValue(String expectedValue) {
        $(By.xpath("//input[@placeholder='999']")).shouldHave(Condition.value(expectedValue));
    }

    public void checkCardNumberInputValue(String expectedValue) {
        $(By.cssSelector(".input__control")).shouldHave(Condition.value(expectedValue));
    }
}
