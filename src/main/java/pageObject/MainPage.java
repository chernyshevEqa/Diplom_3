package pageObject;

import helpers.Helpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    //личный кабинет
    private final By lk =By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private final By loginButtonOnMainPage = By.xpath("//button[text() = 'Войти в аккаунт']");
    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By sauseSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");
    private final By constructor = By.xpath("//p[contains(text(),'Конструктор')]");
    public static final By tagNameCreateBurger = By.xpath("//h1[contains(text(),'Соберите бургер')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        clickElement(lk);
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void openLK() {
        clickElement(lk);
    }

    public void openLoginPageByLoginButtonOnMainPage() {
        clickElement(loginButtonOnMainPage);
    }

    public void clickConstructorButton() {
        clickElement(constructor);
    }

    @Step("переключение секций")
    public WebElement goToSection() throws InterruptedException {
        driver.findElement(fillingsSection).click();
        Thread.sleep(2000);
        driver.findElement(bunsSection).click();
        return driver.findElement(bunsSection);
    }


}
