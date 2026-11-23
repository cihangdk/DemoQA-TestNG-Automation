package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DynamicPropertiesPage;
import utilities.TestBase;

public class DynamicPropertiesTests extends TestBase {

    private DynamicPropertiesPage dynamicPage;

    // Her testten önce yeni page nesnesi oluştur ve sayfayı aç
    @BeforeMethod
    public void setUp() {
        dynamicPage = new DynamicPropertiesPage();
        dynamicPage.sayfayiAc();
    }

    /*
     * Random id'li yazının id'sini okur, sayfayı yeniler ve tekrar okur.
     * Beklenen: İki id farklı olmalı (bu yüzden id ile locator yazılmaz).
     */
    @Test
    public void randomIdHerYuklemedeDegismeli() {
        String ilkId = dynamicPage.randomYazininIdsiniAl();

        dynamicPage.sayfayiYenile();
        String ikinciId = dynamicPage.randomYazininIdsiniAl();

        Assert.assertNotEquals(ikinciId, ilkId, "Sayfa yenilenince id değişmeliydi");
    }

    /*
     * Sayfa açılır açılmaz Enable butonunun durumuna bakar.
     * Beklenen: Buton pasif (disabled) olmalı.
     */
    @Test
    public void enableButonuBastaPasifOlmali() {
        Assert.assertFalse(dynamicPage.enableButonuAktifMi(),
                "Buton başlangıçta pasif olmalıydı");
    }

    /*
     * Enable butonu tıklanabilir olana kadar bekler.
     * Beklenen: Buton aktif olmalı.
     */
    @Test
    public void enableButonuBekleninceAktifOlmali() {
        dynamicPage.enableButonuAktifOlanaKadarBekle();

        Assert.assertTrue(dynamicPage.enableButonuAktifMi());
    }

    /*
     * Sayfa açılır açılmaz Color butonunun class değerine bakar.
     * Beklenen: class "text-danger" içermemeli (henüz kırmızı değil).
     */
    @Test
    public void colorButonuBastaKirmiziOlmamali() {
        String classDegeri = dynamicPage.colorButonununClassiniAl();

        Assert.assertFalse(classDegeri.contains("text-danger"),
                "Buton başlangıçta kırmızı olmamalıydı: " + classDegeri);
    }

    /*
     * Color butonuna text-danger eklenene kadar bekler.
     * Beklenen: class "text-danger" içermeli (yazı kırmızı).
     */
    @Test
    public void colorButonuBekleninceKirmiziOlmali() {
        dynamicPage.colorButonuKirmiziOlanaKadarBekle();

        Assert.assertTrue(dynamicPage.colorButonununClassiniAl().contains("text-danger"));
    }

    /*
     * Başta sayfada olmayan Visible butonunun ortaya çıkmasını bekler.
     * Beklenen: Buton görünür olmalı.
     */
    @Test
    public void visibleButonuBekleninceGorunmeli() {
        Assert.assertTrue(dynamicPage.visibleButonuGorununceDurumunuAl());
    }
}