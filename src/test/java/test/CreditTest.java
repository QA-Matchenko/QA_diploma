package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBUtils;
import data.DataHelper;
import page.PageForm;
import data.Status;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


public class CreditTest {
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
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Payment with approved card, database check")
    void shouldPayAndEntryDB() {
        pageForm.buyOnMoney();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.successfulPageFilling();
        DBUtils.checkPaymentStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Payment with declined card, database check")
    void shouldPayByDeclinedCardStatusInDB() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateDeclinedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
        DBUtils.checkPaymentStatus(Status.DECLINED);
    }

    @Test
    @DisplayName("CardPayment, invalid card number")
    void shouldPayByInvalidCardNumbers2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateInvalidCardNumberWithLettersOrSymbols());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("CardPayment,not enough card numbers")
    void shouldPayByNotEnoughCardNumbers2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateInvalidCardNumberWithShortLength());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("CardPayment,too many card numbers")
    void shouldPayByTooManyCardNumbers2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateInvalidCardNumberWithLongLength());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment,empty card numbers")
    void shouldPayByEmptyCardNumbers2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateEmptyCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("CardPayment, 00 in months")
    void shouldPayBy00InMonth2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateInvalidMonthZero());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("CardPayment, 13 in months")
    void shouldPayBy13InMonth2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateInvalidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("CardPayment, empty in months")
    void shouldPayByEmptyInMonth2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateEmptyString());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.dateWrongFormat();
    }

    @Test
    @DisplayName("CardPayment, 00 in year")
    void shouldPayBy00InYear1() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateInvalidYearZero());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardExpiration();
    }

    @Test
    @DisplayName("CardPayment, Invalid in year")
    void shouldPayByInvalidInYear2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateExpiredYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardExpiration();
    }

    @Test
    @DisplayName("CardPayment,empty in year")
    void shouldPayByEmptyInYear2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateEmptyString());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.cardWrongFormat();
    }

    @Test
    @DisplayName("CardPayment, Cyrillic script in CardOwner")
    void shouldPayByCyrillic2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner("Иван Иванов");
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment, add any symbols in CardOwner")
    void shouldPayByAnySymbols2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner("Иван O8J9O");
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment, empty in CardOwner")
    void shouldPayByEmpty2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateEmptyString());
        pageForm.setCardCVV(DataHelper.generateRandomValidCVV());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment, 1000 in CVC/CVV")
    void shouldPayBy1000CVCCVV2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateBoundaryFourDigitCVV());
        pageForm.pushContinueButton();
        pageForm.successfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment, 4 numbers in CVC/CVV")
    void shouldPayBy4NumbersCVCCVV2() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateFourDigitsCVV());
        pageForm.pushContinueButton();
        pageForm.successfulPageFilling();
    }

    @Test
    @DisplayName("CardPayment, empty in CVC/CVV")
    void shouldPayByEmptyCVCCVV1() {
        pageForm.buyOnCredit();
        pageForm.setCardNumber(DataHelper.generateApprovedCardNumber());
        pageForm.setCardMonth(DataHelper.generateRandomValidMonth());
        pageForm.setCardYear(DataHelper.generateRandomValidYear());
        pageForm.setCardOwner(DataHelper.generateRandomValidOwnerName());
        pageForm.setCardCVV(DataHelper.generateEmptyString());
        pageForm.pushContinueButton();
        pageForm.unSuccessfulPageFilling();
    }
}