package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {

    public static void actionFileds(WebDriver driver, By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);

        try {
            // Ожидаем появления элемента на странице
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            // Перемещаемся к элементу и кликаем по нему
            actions.moveToElement(element).click().perform();

            // Вводим текст в поле ввода
            actions.sendKeys(element, text).perform();
        } catch (TimeoutException e) {
            throw new RuntimeException("Элемент с локатором " + locator + " не найден в течение заданного времени ожидания.", e);
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка при взаимодействии с элементом " + locator, e);
        }
    }

    public static String getTextFromField(WebDriver driver, By locator, String value) {
        return driver.findElement(locator).getAttribute(value);
    }

    public static WebElement waitLocatedElementOnPage(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver,5);
       return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

}
