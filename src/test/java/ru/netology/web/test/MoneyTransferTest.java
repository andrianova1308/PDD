package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    public void SetUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards1() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCardInfo1();
        var secondCard = DataHelper.getCardInfo2();
        int amount = 3000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance01() - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance02() + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCard);
        transferPage.makeTransfer(String.valueOf(amount), firstCard);
        var actualBalanceFirstCard = dashboardPage.getCardBalance01();
        var actualBalanceSecondCard = dashboardPage.getCardBalance02();
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        System.out.println( actualBalanceFirstCard);
        System.out.println( expectedBalanceFirstCard);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards2() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCardInfo1();
        var secondCard = DataHelper.getCardInfo2();
        int amount = 0;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance01() + amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance02() - amount;
        var transferPage = dashboardPage.selectCardToTransfer(firstCard);
        transferPage.makeTransfer(String.valueOf(amount), secondCard);
        var actualBalanceFirstCard = dashboardPage.getCardBalance01();
        var actualBalanceSecondCard = dashboardPage.getCardBalance02();
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards3() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCard = DataHelper.getCardInfo1();
        var secondCard = DataHelper.getCardInfo2();
        int amount = 20000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance01();
        var expectedBalanceSecondCard = dashboardPage.getCardBalance02();
        var transferPage = dashboardPage.selectCardToTransfer(firstCard);
        transferPage.makeTransfer(String.valueOf(amount), secondCard);
        transferPage.TransferError();
        var actualBalanceFirstCard = dashboardPage.getCardBalance01();
        var actualBalanceSecondCard = dashboardPage.getCardBalance02();
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}

