package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class ToolTipsPage {

    // Üzerine gelinecek elementler
    private final By hoverButonu = By.id("toolTipButton");
    private final By hoverMetinKutusu = By.id("toolTipTextField");

    // Hover'dan sonra <body> sonuna eklenen tooltip metinleri
    private final By butonTooltipMetni = By.cssSelector("#buttonToolTip .tooltip-inner");
    private final By metinKutusuTooltipMetni = By.cssSelector("#textFieldToolTip .tooltip-inner");

    // Ayar dosyasındaki adresi açar
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("toolTipsUrl"));
    }

    // Fareyi butonun üzerine getirir
    public void butonunUzerineGel() {
        ReusableMethods.hover(kaydirVeBul(hoverButonu));
    }

    // Fareyi metin kutusunun üzerine getirir
    public void metinKutusununUzerineGel() {
        ReusableMethods.hover(kaydirVeBul(hoverMetinKutusu));
    }

    // Tooltip görünür olana kadar bekleyip metnini döner
    public String butonTooltipMetniniAl() {
        return ReusableMethods.waitForVisibility(butonTooltipMetni).getText();
    }

    public String metinKutusuTooltipMetniniAl() {
        return ReusableMethods.waitForVisibility(metinKutusuTooltipMetni).getText();
    }

    // Ortak iş: elementi bul, görünür alana kaydır, geri döndür
    private WebElement kaydirVeBul(By locator) {
        WebElement element = Driver.getdriver().findElement(locator);
        ReusableMethods.scrollToElement(element);
        return element;
    }
}