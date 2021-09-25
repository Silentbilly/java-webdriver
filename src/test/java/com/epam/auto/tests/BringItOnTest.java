package com.epam.auto.tests;

import com.epam.auto.utils.CustomConditions;
import com.epam.auto.utils.Utils;
import com.epam.auto.utils.WaitingUtils;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page
 * Object. Автоматизировать следующий сценарий:
 * <p>
 * 1. Открыть https://pastebin.com  или аналогичный сервис в любом браузере 2. Создать New Paste со следующими
 * деталями:
 * <p>
 * * Код:
 * <p>
 * git config --global user.name  "New Sheriff in Town" git reset $(git commit-tree HEAD^{tree} -m "Legacy code") git
 * push origin master --force
 * <p>
 * * Syntax Highlighting: "Bash" * Paste Expiration: "10 Minutes" * Paste Name / Title: "how to gain dominance among
 * developers"
 * <p>
 * 3. Сохранить paste и проверить следующее:
 * <p>
 * * Заголовок страницы браузера соответствует Paste Name / Title * Синтаксис подвечен для bash * Проверить что код
 * соответствует введенному в пункте 2
 *
 * @author Alexander Kononov
 */

public class BringItOnTest extends BaseTest {

  final String inputText = "git config --global user.name  \"New Sheriff in Town\"\n"
      + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
      + "git push origin master --force";
  final String pasteName = "how to gain dominance among developers";

  @Test(description = "Проверка результатов Bring It On")
  public void checkResults() {

    driver.get("https://pastebin.com");
    new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXsCompleted());

    WebElement newPasteInput = WaitingUtils.waitForElementLocatedBy(driver, By.id("postform-text"));
    newPasteInput.sendKeys(inputText);

    WebElement syntaxHighlightingBtn = WaitingUtils
        .waitForElementLocatedBy(driver, By.xpath("//label[@for='paste-raw-on']"));
    syntaxHighlightingBtn.click();

    WebElement syntaxHighlighting = WaitingUtils
        .waitForElementLocatedBy(driver, By.id("select2-postform-format-container"));
    syntaxHighlighting.click();
    WebElement syntaxHighlightingBash = WaitingUtils
        .waitForElementLocatedBy(driver, By.xpath("//li[text()='Bash']"));
    syntaxHighlightingBash.click();

    WebElement pasteExpiration = WaitingUtils
        .waitForElementLocatedBy(driver, By.id("select2-postform-expiration-container"));
    pasteExpiration.click();
    WebElement pasteExpirationTenMinutes = WaitingUtils
        .waitForElementLocatedBy(driver, By.xpath("//li[text()='10 Minutes']"));
    pasteExpirationTenMinutes.click();

    WebElement pasteNameInput = driver.findElement(By.id("postform-name"));
    pasteNameInput.sendKeys(pasteName);

    Utils.createNewPasteBin(driver);

    WebElement headerTitle = driver.findElement(By.xpath("//head/meta[@property='og:title']"));
    WebElement pasteResult = WaitingUtils.waitForElementLocatedBy(driver, By.xpath("//textarea[@class='textarea']"));
    List<WebElement> highlightedElementsList = driver.findElements(By.cssSelector("div[class='de1'] span"));

    final boolean isSyntaxHighlighted = highlightedElementsList.stream()
        .anyMatch(webElement -> Objects.equals(webElement.getAttribute("class"), "kw2") &&
            Objects.equals(webElement.getCssValue("color"), "rgba(194, 12, 185, 1)"));

    Assert.assertTrue(headerTitle.getAttribute("content").contains(pasteName));
    Assert.assertEquals(pasteResult.getText(), inputText);
    Assert.assertTrue(isSyntaxHighlighted);
  }
}
