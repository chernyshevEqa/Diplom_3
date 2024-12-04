package pageObject;

import helpers.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static helpers.Helpers.waitLocatedElementOnPage;

public class RecoveryPasswordPage {

    WebDriver driver;

    private static By loginButton = By.xpath("//a[contains(text(),'Войти')]");

    public static void clickLoginButton(WebDriver driver) {
        driver.findElement(loginButton).click();
    }

    public static WebElement waitLoginButton(WebDriver driver) {
        return waitLocatedElementOnPage(driver, loginButton);
    }

}
