package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;
import utilities.TestBase;

public class AlertsTests extends TestBase {

    // Bu sınıftaki tüm testlerin kullanacağı page nesnesi
    private AlertsPage alertsPage;

    // Her testten önce çalışır: page nesnesini yeni tarayıcıya bağlar ve Alerts sayfasını açar
    @BeforeMethod
    public void setUp() {
        alertsPage = new AlertsPage();
        alertsPage.sayfayiAc();
    }

    /*
     * Basit alert
     * Butona basıp açılan uyarının mesajını okuyoruz ve Tamam ile kapatıyoruz.
     * Beklenen: Uyarı mesajı "You clicked a button" olmalı.
     */
    @Test
    public void basitAlertMesajiDogruOlmali() {
        alertsPage.alertButonunaTikla();
        Assert.assertEquals(alertsPage.alertYazisiniAl(), "You clicked a button");
        alertsPage.alertiKabulEt();
    }

    /*
     * Gecikmeli alert
     * Butona basıp 5 saniye sonra açılan uyarıyı bekliyoruz.
     * Beklenen: Uyarı mesajı "This alert appeared after 5 seconds" olmalı.
     */
    @Test
    public void gecikmeliAlertMesajiDogruOlmali() {
        alertsPage.gecikmeliAlertButonunaTikla();
        Assert.assertEquals(alertsPage.alertYazisiniAl(), "This alert appeared after 5 seconds");
        alertsPage.alertiKabulEt();
    }

    /*
     * Confirm - Tamam
     * Onay penceresinde Tamam'a basıyoruz.
     * Beklenen: Sayfada "You selected Ok" yazısı görünmeli.
     */
    @Test
    public void confirmTamamSecilinceSonucOkOlmali() {
        alertsPage.confirmButonunaTikla();
        alertsPage.alertiKabulEt();
        Assert.assertEquals(alertsPage.confirmSonucunuAl(), "You selected Ok");
    }

    /*
     * Confirm - İptal
     * Onay penceresinde İptal'e basıyoruz.
     * Beklenen: Sayfada "You selected Cancel" yazısı görünmeli.
     */
    @Test
    public void confirmIptalSecilinceSonucCancelOlmali() {
        alertsPage.confirmButonunaTikla();
        alertsPage.alertiReddet();
        Assert.assertEquals(alertsPage.confirmSonucunuAl(), "You selected Cancel");
    }

    /*
     * Prompt
     * Yazı kutulu uyarıya isim yazıp Tamam'a basıyoruz.
     * Beklenen: Sayfada "You entered Cihan" yazısı görünmeli.
     */
    @Test
    public void promptaYazilanIsimSayfadaGorunmeli() {
        String isim = "Cihan";
        alertsPage.promptButonunaTikla();
        alertsPage.alerteYaziGonder(isim);
        alertsPage.alertiKabulEt();
        Assert.assertEquals(alertsPage.promptSonucunuAl(), "You entered " + isim);
    }
}