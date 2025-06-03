package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBUtils;
import data.Status;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PageForm;


public class PaymentTest {
    private PageForm pageForm;

    @BeforeEach
    void setUpPage() {
        String appUrl = "http://localhost";
        int appPort = 8080;
        pageForm = new PageForm(appUrl + ":" + appPort);
    }


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() {
        DBUtils.clearAllData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeAllListeners();
    }

    @Test
    @DisplayName("ApprovedCardPayment,valid info")
    void shouldPayByApprovedCard() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("4444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.successfulPageFilling();
    }

    @Test
    @DisplayName("UnapprovedCardPayment,valid info")
    void shouldPayByDeclinedCard() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("4444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();

    }

    @Test
    @DisplayName("ApprovedCardPayment,not enough card numbers")
    void shouldPayByInvalidCardNumbers1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment,not enough card numbers")
    void shouldPayByInvalidCardNumbers2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment,add any symbols")
    void shouldPayByInvalidCardSymbols1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("X444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment,add any symbols")
    void shouldPayByInvalidCardSymbols2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("X444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment,too many card numbers")
    void shouldPayByTooManyCardNumbers1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("4444444444444413");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment,too many card numbers")
    void shouldPayByTooManyCardNumbers2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("4444444444444423");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment,empty card numbers")
    void shouldPayByEmptyCardNumbers1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment,empty card numbers")
    void shouldPayByEmptyCardNumbers2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 00 in months")
    void shouldPayBy00InMonth1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("00");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 00 in months")
    void shouldPayBy00InMonth2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("00");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 13 in months")
    void shouldPayBy13InMonth1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("13");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 13 in months")
    void shouldPayBy13InMonth2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("13");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment, empty in months")
    void shouldPayByEmptyInMonth1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("DeclinedCardPayment, empty in months")
    void shouldPayByEmptyInMonth2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 00 in year")
    void shouldPayBy00InYear1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("00");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 00 in year")
    void shouldPayBy00InYear2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("00");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 24 in year")
    void shouldPayBy24InYear1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("24");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 24 in year")
    void shouldPayBy24InYear2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("24");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 26 in year")
    void shouldPayBy26InYear1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("26");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 26 in year")
    void shouldPayBy26InYear2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("26");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("ApprovedCardPayment,empty in year")
    void shouldPayByEmptyInYear1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("DeclinedCardPayment,empty in year")
    void shouldPayByEmptyInYear2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.cardExpiration();
    }

    @Test
    @DisplayName("ApprovedCardPayment, Cyrillic script in CardOwner")
    void shouldPayByCyrillic1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Иван Иванов");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment, Cyrillic script in CardOwner")
    void shouldPayByCyrillic2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444442");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Иван Иванов");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("ApprovedCardPayment, add any symbols in CardOwner")
    void shouldPayByAnySymbols1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Иван O8J9O");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment, add any symbols in CardOwner")
    void shouldPayByAnySymbols2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Иван O8J9O");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("ApprovedCardPayment,empty in CardOwner")
    void shouldPayByEmpty1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment, empty in CardOwner")
    void shouldPayByEmpty2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("");
        PageForm.setCardCVV("123");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("ApprovedCardPayment, 1000 in CVC/CVV")
    void shouldPayBy1000CVCCVV1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("1000");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 1000 in CVC/CVV")
    void shouldPayBy1000CVCCVV2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("1000");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("ApprovedCardPayment,1234 in CVC/CVV")
    void shouldPayBy1234CVCCVV1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("1234");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment,1234 in CVC/CVV")
    void shouldPayBy1234CVCCVV2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("1234");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("ApprovedCardPayment, empty in CVC/CVV")
    void shouldPayByEmptyCVCCVV1() {
        PageForm.buyOnMoney();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("DeclinedCardPayment, 1000 in CVC/CVV")
    void shouldPayByEmptyCVCCVV2() {
        PageForm.buyOnCredit();
        PageForm.setCardNumber("444444444444441");
        PageForm.setCardMonth("07");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Ivan Ivanov");
        PageForm.setCardCVV("");
        PageForm.pushContinueButton();
        PageForm.unSuccessfulPageFilling();
    }
}