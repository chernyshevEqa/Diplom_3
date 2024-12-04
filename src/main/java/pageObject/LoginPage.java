package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static helpers.Helpers.actionFileds;

public class LoginPage {
    WebDriver driver;

    private By emailField = By.xpath("//label[contains(text(),'Email')]");
    private By passwordField = By.xpath("//label[contains(text(),'Пароль')]");
    private By enterButton = By.xpath("//button[contains(text(),'Войти')]");
    public static By recoveryButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    public static By loginTitle = By.xpath("//h2[contains(text(),'Вход')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void sentKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    @Step("логин в систему")
    public void login(String email, String password) {
        actionFileds(driver,emailField,email);
        actionFileds(driver,passwordField, password);
        clickElement(enterButton);
    }

    @Step("логин в систему с кастомным паролем")
    public void loginTest(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(enterButton).click();
    }

}
