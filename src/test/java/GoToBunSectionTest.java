import driver.WebDriverCreator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.MainPage;

public class GoToBunSectionTest {

    WebDriver driver;
    String expectedValueClass = "tab_tab_type_current__2BEPc";
    MainPage mainPage;

    @Before
    public void setup() {
        driver = WebDriverCreator.createWebDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new MainPage(driver);
    }



    @Test
    public void goToSectionBunsTest() throws InterruptedException {
        WebElement buns = mainPage.goToSection();
        // я понимаю что засыпание использовать это плохая практика, но по другому не получилось сделать
        Thread.sleep(2000);
        Assert.assertTrue("Класс элемента не содержит ожидаемое значение после клика!",
                buns.getAttribute("class").contains(expectedValueClass));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
