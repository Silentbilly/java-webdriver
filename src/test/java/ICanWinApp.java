import com.epam.auto.utils.CustomConditions;
import com.epam.auto.utils.WaitingUtils;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page
 * Object. Автоматизировать следующий сценарий: 1. Открыть https://pastebin.com или аналогичный сервис в любом браузере
 * 2. Создать New Paste со следующими деталями:
 * <p>
 * * Код: "Hello from WebDriver" * Paste Expiration: "10 Minutes" * Paste Name / Title: "helloweb"
 *
 * @author Alexander Kononov
 */

public class ICanWinApp {

  public static void main(String[] args) throws InterruptedException {

    WebDriver driver = new ChromeDriver();

    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(15))
        .pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException.class)
        .ignoring(StaleElementReferenceException.class)
        .withMessage("Timeout for waiting search result list was exceeded");

    driver.get("https://pastebin.com");
    new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXsCompleted());

    WebElement newPasteInput = WaitingUtils.waitForElementLocatedBy(driver, By.id("postform-text"));
    newPasteInput.sendKeys("Hello from WebDriver");

    WebElement pasteExpiration = WaitingUtils
        .waitForElementLocatedBy(driver, By.id("select2-postform-expiration-container"));
    pasteExpiration.click();

    List<WebElement> pasteExpirationDropdownList = WaitingUtils
        .waitForAllElementsLocatedBy(driver, By.className("select2-results__option"));
    pasteExpirationDropdownList.get(2).click();

    WebElement pasteNameInput = driver.findElement(By.id("postform-name"));
    pasteNameInput.sendKeys("helloweb");

    List<WebElement> createNewPasteBtn = driver
        .findElements(By.xpath("//button[text()='Create New Paste']"));
    createNewPasteBtn.get(0).click();

    List<WebElement> pasteResults = wait.until(new Function<WebDriver, List<WebElement>>() {
      public List<WebElement> apply(WebDriver driver) {
        List<WebElement> list = driver
            .findElements(By.xpath("//div[text()='Hello from WebDriver' and @class='de1']"));
        if (list.size() == 0) {
          return null;
        } else {
          return list;
        }
      }
    });
    System.out.println("Number of results: " + pasteResults.size());

    driver.quit();
  }
}
