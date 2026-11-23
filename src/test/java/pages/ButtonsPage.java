package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class ButtonsPage {

    // Butonlar
    private final By ciftTiklamaButonu = By.id("doubleClickBtn");
    private final By sagTiklamaButonu = By.id("rightClickBtn");
    // id'si her yüklemede değiştiği için tam metne göre buluyoruz
    private final By clickMeButonu = By.xpath("//button[text()='Click Me']");

    // Doğru tıklamadan sonra çıkan mesajlar
    private final By ciftTiklamaMesaji = By.id("doubleClickMessage");
    private final By sagTiklamaMesaji = By.id("rightClickMessage");
    private final By dinamikTiklamaMesaji = By.id("dynamicClickMessage");

    // Ayar dosyasındaki adresi açar
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("buttonsUrl"));
    }

    // Double Click Me butonuna çift tıklar
    public void ciftTiklamaButonunaCiftTikla() {
        ReusableMethods.doubleClick(kaydirVeBul(ciftTiklamaButonu));
    }

    // Right Click Me butonuna sağ tıklar
    public void sagTiklamaButonunaSagTikla() {
        ReusableMethods.rightClick(kaydirVeBul(sagTiklamaButonu));
    }

    // Click Me butonuna normal (tek) tıklar
    public void clickMeButonunaTikla() {
        kaydirVeBul(clickMeButonu).click();
    }

    // Mesajlar görünür olana kadar bekleyip metnini döner
    public String ciftTiklamaMesajiniAl() {
        return ReusableMethods.waitForVisibility(ciftTiklamaMesaji).getText();
    }

    public String sagTiklamaMesajiniAl() {
        return ReusableMethods.waitForVisibility(sagTiklamaMesaji).getText();
    }

    public String dinamikTiklamaMesajiniAl() {
        return ReusableMethods.waitForVisibility(dinamikTiklamaMesaji).getText();
    }

    // Ortak iş: elementi bul, görünür alana kaydır, geri döndür
    private WebElement kaydirVeBul(By locator) {
        WebElement element = Driver.getdriver().findElement(locator);
        ReusableMethods.scrollToElement(element);
        return element;
    }
}