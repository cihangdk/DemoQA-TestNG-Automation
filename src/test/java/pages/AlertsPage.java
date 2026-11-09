package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class AlertsPage {

    // Constructor: sınıftaki @FindBy ile işaretli tüm elementleri açık olan tarayıcıya bağlar
    public AlertsPage() {
        PageFactory.initElements(Driver.getdriver(), this);
    }

    // ---------- Elementler (private: testler doğrudan erişemez, sadece aşağıdaki metotlarla kullanılır) ----------

    // Basit alert açan buton
    @FindBy(id = "alertButton")
    private WebElement alertButton;

    // 5 saniye sonra alert açan buton
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;

    // Confirm (Tamam / İptal) açan buton
    @FindBy(id = "confirmButton")
    private WebElement confirmButton;

    // Prompt (yazı kutulu) açan buton (sitedeki id yazım hatalı: promtButton)
    @FindBy(id = "promtButton")
    private WebElement promptButton;

    // Confirm cevabından sonra çıkan sonuç yazısı
    @FindBy(id = "confirmResult")
    private WebElement confirmResult;

    // Prompt'a yazılan ismin göründüğü sonuç yazısı
    @FindBy(id = "promptResult")
    private WebElement promptResult;

    // ---------- Sayfa metotları ----------

    // Alerts sayfasını ayar dosyasındaki adresle açar
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("alertsUrl"));
    }

    // Butonlara tıklama metotları: önce elementi ekranın ortasına kaydırır, sonra tıklar
    public void alertButonunaTikla() {
        ReusableMethods.scrollToElement(alertButton);
        alertButton.click();
    }

    public void gecikmeliAlertButonunaTikla() {
        ReusableMethods.scrollToElement(timerAlertButton);
        timerAlertButton.click();
    }

    public void confirmButonunaTikla() {
        ReusableMethods.scrollToElement(confirmButton);
        confirmButton.click();
    }

    public void promptButonunaTikla() {
        ReusableMethods.scrollToElement(promptButton);
        promptButton.click();
    }

    // Alert metotları: alert açılana kadar bekler, sonra işlemi yapar
    public String alertYazisiniAl() {
        return ReusableMethods.waitForAlert().getText();
    }

    public void alertiKabulEt() {
        ReusableMethods.waitForAlert().accept();
    }

    public void alertiReddet() {
        ReusableMethods.waitForAlert().dismiss();
    }

    public void alerteYaziGonder(String yazi) {
        ReusableMethods.waitForAlert().sendKeys(yazi);
    }

    // Sonuç yazılarını okuma metotları
    public String confirmSonucunuAl() {
        return confirmResult.getText();
    }

    public String promptSonucunuAl() {
        return promptResult.getText();
    }
}