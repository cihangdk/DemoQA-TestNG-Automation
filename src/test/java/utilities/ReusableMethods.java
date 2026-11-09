package utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableMethods {

    // Constructor private: Sadece static metotlar içerdiği için nesne oluşturulmasını engeller
    private ReusableMethods() {
    }

    // Bekleme süresini ayar dosyasından okuyup bir WebDriverWait nesnesi oluşturur
    private static WebDriverWait getWait() {
        long explicitWait = Long.parseLong(ConfigReader.getProperty("explicitWait"));
        return new WebDriverWait(Driver.getdriver(), Duration.ofSeconds(explicitWait));
    }

    // Element görünür olana kadar bekler ve elementi döndürür
    public static WebElement waitForVisibility(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    // Element tıklanabilir olana kadar bekler ve elementi döndürür
    public static WebElement waitForClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // Alert açılana kadar bekler ve Alert nesnesini döndürür
    public static Alert waitForAlert() {
        return getWait().until(ExpectedConditions.alertIsPresent());
    }

    // Elementi sayfada görünür alana kaydırır
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getdriver())
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // Elemente JavaScript ile tıklar (üstüne reklam gibi başka bir element geldiğinde normal tıklama çalışmaz)
    public static void jsClick(WebElement element) {
        ((JavascriptExecutor) Driver.getdriver())
                .executeScript("arguments[0].click();", element);
    }
}