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
import utils.GeneratorUsers;

import static helpers.Helpers.waitLocatedElementOnPage;
import static pageObject.MainPage.tagNameCreateBurger;


public class MoveToConstructorFromPrivateAccountTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private CreateUserPojo userPojo;
    private ApiClient client;
    public String token;


    @Before
    public void setUp() {
        client = new ApiClient();
        driver = WebDriverCreator.createWebDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userPojo = GeneratorUsers.randomUser();
        token = client.registerUser(userPojo);
    }

    @Test
    @Description("переход в конструктор")
    public void moveToConstructorFromPrivateAccountTest() {
        driver.get("https://stellarburgers.nomoreparties.site");

        //открываем личный кабинет и логинемся
        mainPage.openLK();
        loginPage.login(userPojo.getEmail(), userPojo.getPassword());

        //открываем личный кабинет чтобы убедиться что вошли
        mainPage.openLK();

        mainPage.clickConstructorButton();

        //ожидаем пока элемент появится на странице
        waitLocatedElementOnPage(driver, tagNameCreateBurger);

        //получаем значение поля емейл и проверям его с ожидаемым
        Assert.assertNotNull("Element is not visible on the page!", tagNameCreateBurger);
    }

    @After
    public void tearDown() {
        client.deleteUser();
        driver.quit();
    }
}
