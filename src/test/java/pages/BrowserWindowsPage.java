package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class BrowserWindowsPage {

    // Butonlar
    private final By yeniSekmeButonu = By.id("tabButton");
    private final By yeniPencereButonu = By.id("windowButton");

    // Yeni açılan sample sayfasındaki başlık
    private final By yeniSayfaBasligi = By.id("sampleHeading");

    // Ana pencerenin kimliği (handle); geri dönmek için saklıyoruz
    private String anaPencereHandle;

    // Sayfayı aç ve ana pencerenin kimliğini kaydet
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("windowsUrl"));
        anaPencereHandle = Driver.getdriver().getWindowHandle();
    }

    // Butonu görünür alana kaydırıp tıklar
    public void yeniSekmeButonunaTikla() {
        butonaTikla(yeniSekmeButonu);
    }

    public void yeniPencereButonunaTikla() {
        butonaTikla(yeniPencereButonu);
    }

    // İkinci pencere açılana kadar bekle, sonra ana pencere olmayana geç
    public void yeniPencereyeGec() {
        ReusableMethods.waitForNumberOfWindows(2);
        for (String handle : Driver.getdriver().getWindowHandles()) {
            if (!handle.equals(anaPencereHandle)) {
                Driver.getdriver().switchTo().window(handle);
                break;
            }
        }
    }

    // Aktif penceredeki başlığı okur
    public String yeniPenceredekiBasligiAl() {
        return Driver.getdriver().findElement(yeniSayfaBasligi).getText();
    }

    // Driver'ın şu an bulunduğu pencerenin adresi
    public String aktifAdresiAl() {
        return Driver.getdriver().getCurrentUrl();
    }

    // Açık pencere/sekme sayısı
    public int acikPencereSayisiniAl() {
        return Driver.getdriver().getWindowHandles().size();
    }

    // Sadece aktif pencereyi kapat, sonra kaydedilen ana pencereye dön
    public void yeniPencereyiKapatVeAnaPencereyeDon() {
        Driver.getdriver().close();
        Driver.getdriver().switchTo().window(anaPencereHandle);
    }

    // Ortak tıklama: önce kaydır, sonra tıkla
    private void butonaTikla(By buton) {
        WebElement element = Driver.getdriver().findElement(buton);
        ReusableMethods.scrollToElement(element);
        element.click();
    }
}