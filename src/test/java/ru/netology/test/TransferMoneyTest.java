package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTest {
        @Test
        void shouldTransferMoneyFromFirstToSecond() {
                open("http://localhost:9999");
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                val dashboardPage = verificationPage.validVerify(verificationCode);
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 100;
                val transferPage = dashboardPage.addToFirstCard();
                transferPage.addToFirstCard(transfer);
                val expectBalanceFirstCard = balanceFirstCard + transfer;
                val expectBalanceSecondCard = balanceSecondCard - transfer;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

        @Test
        void shouldTransferMoneyFromSecondToFirst() {
                open("http://localhost:9999");
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                val dashboardPage = verificationPage.validVerify(verificationCode);
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 100;
                val transferPage = dashboardPage.addToSecondCard();
                transferPage.addToSecondCard(transfer);
                val expectBalanceFirstCard = balanceFirstCard - transfer;
                val expectBalanceSecondCard = balanceSecondCard + transfer;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

        @Test
        void shouldAbortButtonFromFirstToSecond() {
                open("http://localhost:9999");
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                val dashboardPage = verificationPage.validVerify(verificationCode);
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 100;
                val transferPage = dashboardPage.addToSecondCard();
                transferPage.abordToSecondCard(transfer);
                val expectBalanceFirstCard = balanceFirstCard;
                val expectBalanceSecondCard = balanceSecondCard;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

        @Test
        void shouldAbortButtonFromSecondToFirst() {
                open("http://localhost:9999");
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                val dashboardPage = verificationPage.validVerify(verificationCode);
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 100;
                val transferPage = dashboardPage.addToFirstCard();
                transferPage.abordToFirstCard(transfer);
                val expectBalanceFirstCard = balanceFirstCard;
                val expectBalanceSecondCard = balanceSecondCard;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

        @Test
        void shouldNotTransferMoneyFromFirstToSecond() {
                open("http://localhost:9999");
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                val dashboardPage = verificationPage.validVerify(verificationCode);
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 10001;
                val transferPage = dashboardPage.addToFirstCard();
                transferPage.addToFirstCard(transfer);
                val expectBalanceFirstCard = balanceFirstCard;
                val expectBalanceSecondCard = balanceSecondCard;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

        @Test
        void shouldNotTransferMoneyFromSecondToFirst() {
                //подключаемся к серверу
                open("http://localhost:9999");
                //логинимся
                val loginPage = new LoginPage();
                val authInfo = DataHelper.getAuthInfo();
                val verificationPage = loginPage.validLogin(authInfo);
                val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
                //проверяем подключение к Dashboard и создаем переменную класса DashboardPage для тестов
                val dashboardPage = verificationPage.validVerify(verificationCode);
                //настройка параметров перевода
                val balanceFirstCard = dashboardPage.getFirstCardBalance();
                val balanceSecondCard = dashboardPage.getSecondCardBalance();
                Integer transfer = 10001;
                //выбираем первую карту
                val transferPage = dashboardPage.addToSecondCard();
                //переводим средства
                transferPage.addToSecondCard(transfer);
                //проверяем результат
                val expectBalanceFirstCard = balanceFirstCard;
                val expectBalanceSecondCard = balanceSecondCard;
                assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
                assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
        }

}

