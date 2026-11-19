package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import utilities.TestBase;

public class BrowserWindowsTests extends TestBase {

    private BrowserWindowsPage windowsPage;

    // Her testten önce yeni page nesnesi oluştur ve sayfayı aç
    @BeforeMethod
    public void setUp() {
        windowsPage = new BrowserWindowsPage();
        windowsPage.sayfayiAc();
    }

    /*
     * New Tab butonuna tıklar ve yeni sekmeye geçer.
     * Beklenen: Açık pencere sayısı 2 olmalı.
     */
    @Test
    public void yeniSekmeButonuIkinciPencereAcmali() {
        windowsPage.yeniSekmeButonunaTikla();
        windowsPage.yeniPencereyeGec();

        Assert.assertEquals(windowsPage.acikPencereSayisiniAl(), 2);
    }

    /*
     * New Tab ile açılan sekmeye geçip adresi okur.
     * Beklenen: Adres "/sample" ile bitmeli (driver gerçekten yeni sekmede).
     */
    @Test
    public void yeniSekmeyeGecinceAdresSampleOlmali() {
        windowsPage.yeniSekmeButonunaTikla();
        windowsPage.yeniPencereyeGec();

        String adres = windowsPage.aktifAdresiAl();
        Assert.assertTrue(adres.endsWith("/sample"),
                "Yeni sekmenin adresi beklenen değil: " + adres);
    }

    /*
     * New Tab ile açılan sekmedeki başlığı okur.
     * Beklenen: Başlık "This is a sample page" olmalı.
     */
    @Test
    public void yeniSekmedekiBaslikDogruOlmali() {
        windowsPage.yeniSekmeButonunaTikla();
        windowsPage.yeniPencereyeGec();

        Assert.assertEquals(windowsPage.yeniPenceredekiBasligiAl(), "This is a sample page");
    }

    /*
     * New Window ile açılan penceredeki başlığı okur.
     * Beklenen: Başlık "This is a sample page" olmalı (sekme ile pencere aynı şekilde yönetilir).
     */
    @Test
    public void yeniPenceredekiBaslikDogruOlmali() {
        windowsPage.yeniPencereButonunaTikla();
        windowsPage.yeniPencereyeGec();

        Assert.assertEquals(windowsPage.yeniPenceredekiBasligiAl(), "This is a sample page");
    }

    /*
     * Yeni sekmeyi açar, kapatır ve ana pencereye döner.
     * Beklenen: Tek pencere kalmalı ve adres browser-windows sayfası olmalı.
     */
    @Test
    public void yeniSekmeKapatilincaAnaPencereyeDonulmeli() {
        windowsPage.yeniSekmeButonunaTikla();
        windowsPage.yeniPencereyeGec();
        windowsPage.yeniPencereyiKapatVeAnaPencereyeDon();

        Assert.assertEquals(windowsPage.acikPencereSayisiniAl(), 1);
        Assert.assertTrue(windowsPage.aktifAdresiAl().contains("browser-windows"),
                "Ana pencereye dönülmedi");
    }
}