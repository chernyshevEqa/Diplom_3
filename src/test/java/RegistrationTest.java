import com.github.javafaker.Faker;
import driver.WebDriverCreator;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import javax.management.DescriptorKey;
import java.time.Duration;


public class RegistrationTest {

    private WebDriver driver;
    RegistrationPage registration;
    Faker faker;
    LoginPage loginPage;
    MainPage mainPage;
    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        registration = new RegistrationPage(driver);
        faker = new Faker();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @Description("успешная регистрация")
    public void registrationTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registration.registration();
        mainPage.openLK();
        loginPage.login(registration.getRegisteredEmail(), registration.getRegisteredPassword());
        mainPage.openLK();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("profile"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("profile"));
    }

    @Test
    @Description("не успешная регистрация пароль менее 6 символов")
    public void nonSuccessfulRegistrationTest() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        registration.registration(faker.internet().password(3,5));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Некорректный пароль')]")));
        Assert.assertTrue("Ошибка с некорректным паролем не отображается", errorMessage.isDisplayed());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
