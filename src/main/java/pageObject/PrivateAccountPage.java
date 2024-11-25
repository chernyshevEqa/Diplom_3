package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrivateAccountPage {
    WebDriver driver;

    private void setUp() {
        driver = new ChromeDriver();
    }

    public PrivateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public static By emailField = By.xpath("//label[contains(text(), 'Логин')]/following-sibling::input");
    public static By logOutButton = By.xpath("//button[contains(text(),'Выход')]");

    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }


}
