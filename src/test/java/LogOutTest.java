import driver.WebDriverCreator;
import io.qameta.allure.Description;
import models.CreateUserPojo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PrivateAccountPage;
import utils.GeneratorUsers;

import static helpers.Helpers.waitLocatedElementOnPage;
import static pageObject.LoginPage.loginTitle;
import static pageObject.PrivateAccountPage.logOutButton;

public class LogOutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CreateUserPojo userPojo;
    private ApiClient client;
    PrivateAccountPage accountPage;

    @Before
    public void setup() {
        client = new ApiClient();
        driver = WebDriverCreator.createWebDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userPojo = GeneratorUsers.randomUser();
        accountPage = new PrivateAccountPage(driver);
        client.registerUser(userPojo);
    }

    @Test
    @Description("выход по кнопке «Выйти» в личном кабинете")
    public void logOutUsingLogOutButtonFromPrivateAccountTest() {
        driver.get("https://stellarburgers.nomoreparties.site");

        //открываем личный кабинет
        mainPage.openLK();

        //логинемся
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());

        //открываем личный кабинет
        mainPage.openLK();
        //ждем пока элемент появится на странице
        waitLocatedElementOnPage(driver, logOutButton);

        //выходим из личного кабинет
        accountPage.clickLogOutButton();

        //ждем пока элемент появится на странице
        waitLocatedElementOnPage(driver, loginTitle);
        WebElement element = driver.findElement(loginTitle);

        //проверяем что элемент есть на стринице
        Assert.assertTrue(element.isDisplayed());
    }

    @After
    public void tearDown() {
        client.deleteUser();
        driver.quit();
    }
}
