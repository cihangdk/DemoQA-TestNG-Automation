package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class DynamicPropertiesPage {

    // id'si her yüklemede değiştiği için yazısına göre buluyoruz
    private final By randomIdYazisi = By.xpath("//p[text()='This text has random Id']");

    // 5 saniye sonra değişen butonlar
    private final By enableButonu = By.id("enableAfter");
    private final By colorButonu = By.id("colorChange");
    private final By visibleButonu = By.id("visibleAfter");

    // Ayar dosyasındaki adresi açar
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("dynamicPropertiesUrl"));
    }

    // Sayfayı yeniler (random id'nin değiştiğini görmek için)
    public void sayfayiYenile() {
        Driver.getdriver().navigate().refresh();
    }

    // Random id'li yazının o anki id değerini döner
    public String randomYazininIdsiniAl() {
        return Driver.getdriver().findElement(randomIdYazisi).getAttribute("id");
    }

    // Enable butonu şu an tıklanabilir mi (beklemeden)
    public boolean enableButonuAktifMi() {
        return Driver.getdriver().findElement(enableButonu).isEnabled();
    }

    // Enable butonu tıklanabilir olana kadar bekler
    public void enableButonuAktifOlanaKadarBekle() {
        WebElement buton = Driver.getdriver().findElement(enableButonu);
        ReusableMethods.waitForClickable(buton);
    }

    // Color butonunun şu anki class değerini döner (beklemeden)
    public String colorButonununClassiniAl() {
        return Driver.getdriver().findElement(colorButonu).getAttribute("class");
    }

    // Color butonuna kırmızı yazı sınıfı (text-danger) eklenene kadar bekler
    public void colorButonuKirmiziOlanaKadarBekle() {
        WebElement buton = Driver.getdriver().findElement(colorButonu);
        ReusableMethods.waitForAttributeContains(buton, "class", "text-danger");
    }

    // Visible butonu görünür olana kadar bekler, sonra görünürlüğünü döner
    public boolean visibleButonuGorununceDurumunuAl() {
        return ReusableMethods.waitForVisibility(visibleButonu).isDisplayed();
    }
}