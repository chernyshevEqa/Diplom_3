package pageObject;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static helpers.Helpers.actionFileds;

public class RegistrationPage {
    WebDriver driver;
    Faker faker = new Faker();

    private By name = By.xpath("//label[text()='Имя']");
    private By email = By.xpath("//label[text()='Email']");
    private By password = By.name("Пароль");
    private By registrationButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private By errorIncorrectPassword = By.xpath("//p[text() = 'Некорректный пароль']");
    public static By loginButton = By.xpath("//a[contains(text(),'Войти')]");


    private String registeredName;
    private String registeredEmail;
    private String registeredPassword;

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void sentKeys(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

    @Step("регистрация с кастомный паролем")
    public void registration(String customPassword)  {
        registeredName = faker.name().firstName();
        registeredEmail = faker.internet().emailAddress();
        registeredPassword = customPassword;

        actionFileds(driver, name, registeredName);
        actionFileds(driver, email, registeredEmail);
        actionFileds(driver, password, registeredPassword);
        clickElement(registrationButton);
    }

    @Step("регистрация")
    public void registration()  {
        registeredName = faker.name().firstName();
        registeredEmail = faker.internet().emailAddress();
        registeredPassword = faker.internet().password(10,20);

        actionFileds(driver, name, registeredName);
        actionFileds(driver, email, registeredEmail);
        actionFileds(driver, password, registeredPassword);
        clickElement(registrationButton);
    }
}
