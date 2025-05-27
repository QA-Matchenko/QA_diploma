package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.PageForm;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.dbutils.DbUtils;
import org.junit.jupiter.api.*;

import java.awt.print.PageFormat;

class PaymentTest {
    @BeforeEach
    void setUpPage() {
        PageForm PageForm = new PageForm();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() {
        clearAll();
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeAllListeners();
    }

    @Test
    @DisplayName("ApprovedCardPayment")
     void shouldPayByApprovedCard() {
        PageForm.byuOnMoney();
        PageForm.setCardNumber("4444444444444441");
        PageForm.setCardMonth("08");
        PageForm.setCardYear("25");
        PageForm.setCardOwner("Maria Ivanova");
        PageForm.setCardCVV("999");
        PageForm.pushСontinueButton();
    }
}