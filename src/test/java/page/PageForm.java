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

    List<SelenideElement> input = Selenide.$$(".input__control");
    SelenideElement cardNumber = input.get(0);
    SelenideElement month = input.get(1);
    SelenideElement year = input.get(2);
    SelenideElement cardOwner = input.get(3);
    SelenideElement cvcOrCvvNumber = input.get(4);

    public void buyOnMoney() {
        $$(".button__content").find(exactText("Купить")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);

    }

    public void buyOnCredit() {
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Кредит по данным карты")).shouldBe(visible);

    }

    public void cardWrongFormat() {
        $$(".input__sub").find(exactText("Неверный формат")).shouldBe(visible);
    }


    public void dateWrongFormat() {
        $$(".input__sub").find(exactText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void cardExpiration() {
        $$(".input__sub").find(exactText("Истек срок действия карты")).shouldBe(visible);

    }

    public void successfulPageFilling() {
        $(Selectors.withText("Операция одобрена банком")).shouldBe(visible, Duration.ofSeconds(15));
    }


    public void unSuccessfulPageFilling() {
        $(Selectors.withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void continuePageFilling() {
        $$(".button__content").find(exactText("Продолжить")).click();
    }

    public void setCardNumber(String cNumber) {
        cardNumber.setValue(cNumber);
    }

    public void setCardMonth(String cMonth) {
        month.setValue(cMonth);
    }

    public void setCardYear(String cYear) {
        year.setValue(cYear);
    }

    public void setCardOwner(String cOwner) {
        cardOwner.setValue(cOwner);
    }

    public void setCardCVV(String cCvv) {
        cvcOrCvvNumber.setValue(cCvv);
    }

    public void pushContinueButton() {
        $$(".button__content").find(exactText("Продолжить")).click();
    }
}
