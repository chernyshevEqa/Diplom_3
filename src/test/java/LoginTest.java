import driver.WebDriverCreator;
import io.qameta.allure.Description;
import models.CreateUserPojo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RecoveryPasswordPage;
import pageObject.RegistrationPage;
import utils.GeneratorUsers;

import static helpers.Helpers.getTextFromField;
import static helpers.Helpers.waitLocatedElementOnPage;
import static pageObject.LoginPage.recoveryButton;
import static pageObject.PrivateAccountPage.emailField;
import static pageObject.RecoveryPasswordPage.waitLoginButton;
import static pageObject.RegistrationPage.loginButton;

public class LoginTest {

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CreateUserPojo userPojo;
    private ApiClient client;

    @Before
    public void setup() {
        client = new ApiClient();
        driver = WebDriverCreator.createWebDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userPojo = GeneratorUsers.randomUser();
    }

    @Test
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void loginInAccountByLoginButtonOnMainPageTest() {

        driver.get("https://stellarburgers.nomoreparties.site");
        // открываем станицу логина через кнопку на главной страницу
        mainPage.openLoginPageByLoginButtonOnMainPage();
        //логинемся
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());

        //открываем личный кабинет
        mainPage.openLK();

        //ожидаем пока элемент появится на странице
        waitLocatedElementOnPage(driver, emailField);

        //получаем значение поля емейл и проверям его с ожидаемым
        String actualEmail = getTextFromField(driver, emailField, "value");
        Assert.assertEquals(userPojo.getEmail(), actualEmail);
    }

    @Test
    @Description("вход через кнопку «Личный кабинет»")
    public void loginUsingPersonalAccountTest()  {

        //регистрируемся сразу по ссылке на регистрацию
        driver.get("https://stellarburgers.nomoreparties.site");

        //открываем личный кабинет и логинемся
        mainPage.openLK();
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());

        //открываем личный кабинет чтобы убедиться что вошли
        mainPage.openLK();

        //ожидаем пока элемент появится на странице
        waitLocatedElementOnPage(driver, emailField);

        //получаем значение поля емейл и проверям его с ожидаемым
        String actualEmail = getTextFromField(driver, emailField, "value");
        Assert.assertEquals(userPojo.getEmail(), actualEmail);
    }

    @Test
    @Description("вход через кнопку в форме регистрации")
    public void loginUsingRegistrationFormTest() {

        //регистрируемся сразу по ссылке на регистрацию
        driver.get("https://stellarburgers.nomoreparties.site/register");

        //переходим на страницу входа через кнопку войти в форме регистрации
        registrationPage.clickElement(loginButton);

        //логинемся
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());
        mainPage.openLK();

        //ожидаем пока элемент появится на странице
        waitLocatedElementOnPage(driver, emailField);

        //получаем значение поля емейл и проверям его с ожидаемым
        String actualEmail = getTextFromField(driver, emailField, "value");
        Assert.assertEquals(userPojo.getEmail(), actualEmail);
    }

    @Test
    @Description("вход через кнопку в форме восстановления пароля")
    public void loginUsingRecoveryPasswordFormTest() throws InterruptedException {

        driver.get("https://stellarburgers.nomoreparties.site/login");

        //переходим на страницу восстановления пароля
        waitLocatedElementOnPage(driver, recoveryButton);
        loginPage.clickElement(recoveryButton);

        //переходим на страницу логина
        waitLoginButton(driver);
        RecoveryPasswordPage.clickLoginButton(driver);

        //логинемся
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());
        mainPage.openLK();

        //ожидаем пока элемент появится на странице
        waitLocatedElementOnPage(driver, emailField);

        //получаем значение поля емейл и проверям его с ожидаемым
        String actualEmail = getTextFromField(driver, emailField, "value");
        Assert.assertEquals(userPojo.getEmail(), actualEmail);
    }



    @After
    public void tearDown() {
        client.deleteUser();
        driver.quit();
    }

}
