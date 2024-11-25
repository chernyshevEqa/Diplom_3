
import driver.WebDriverCreator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GoToSectionTest {

    WebDriver driver;
    String locator;
    String expectedValueClass = "tab_tab_type_current__2BEPc";

    public GoToSectionTest(String locator) {
        this.locator = locator;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data () {
        return Arrays.asList(new Object[][]{
                {"//span[text()='Соусы']/parent::div"},
                {"//span[text()='Начинки']/parent::div"},
        });
    }

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    public void goToSectionTest()  {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        tab.click();

        // Повторно находим элемент, чтобы убедиться, что атрибут изменился после клика
        tab = driver.findElement(By.xpath(locator));
        Assert.assertTrue("Класс элемента не содержит ожидаемое значение после клика!",
                tab.getAttribute("class").contains(expectedValueClass));
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
