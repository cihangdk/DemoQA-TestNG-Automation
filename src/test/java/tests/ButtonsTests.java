package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ButtonsPage;
import utilities.TestBase;

public class ButtonsTests extends TestBase {

    private ButtonsPage buttonsPage;

    // Her testten önce yeni page nesnesi oluştur ve sayfayı aç
    @BeforeMethod
    public void setUp() {
        buttonsPage = new ButtonsPage();
        buttonsPage.sayfayiAc();
    }

    /*
     * Double Click Me butonuna Actions ile çift tıklar.
     * Beklenen: "You have done a double click" mesajı görünmeli.
     */
    @Test
    public void ciftTiklayincaCiftTiklamaMesajiGorunmeli() {
        buttonsPage.ciftTiklamaButonunaCiftTikla();

        Assert.assertEquals(buttonsPage.ciftTiklamaMesajiniAl(), "You have done a double click");
    }

    /*
     * Right Click Me butonuna Actions ile sağ tıklar.
     * Beklenen: "You have done a right click" mesajı görünmeli.
     */
    @Test
    public void sagTiklayincaSagTiklamaMesajiGorunmeli() {
        buttonsPage.sagTiklamaButonunaSagTikla();

        Assert.assertEquals(buttonsPage.sagTiklamaMesajiniAl(), "You have done a right click");
    }

    /*
     * id'si değişken olan Click Me butonuna normal tıklar.
     * Beklenen: "You have done a dynamic click" mesajı görünmeli.
     */
    @Test
    public void clickMeTiklayincaDinamikTiklamaMesajiGorunmeli() {
        buttonsPage.clickMeButonunaTikla();

        Assert.assertEquals(buttonsPage.dinamikTiklamaMesajiniAl(), "You have done a dynamic click");
    }
}