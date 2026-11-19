package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FramesPage;
import utilities.TestBase;

public class FramesTests extends TestBase {

    private FramesPage framesPage;

    // Her testten önce yeni page nesnesi oluştur ve Frames sayfasını aç
    @BeforeMethod
    public void setUp() {
        framesPage = new FramesPage();
        framesPage.sayfayiAc();
    }

    /*
     * Büyük frame'e (frame1) girip içindeki başlığı okur.
     * Beklenen: Başlık "This is a sample page" olmalı.
     */
    @Test
    public void buyukFrameBasligiDogruOlmali() {
        String baslik = framesPage.buyukFrameBasliginiAl();
        Assert.assertEquals(baslik, "This is a sample page");
    }

    /*
     * Küçük frame'e (frame2) girip içindeki başlığı okur.
     * Beklenen: Başlık "This is a sample page" olmalı.
     */
    @Test
    public void kucukFrameBasligiDogruOlmali() {
        String baslik = framesPage.kucukFrameBasliginiAl();
        Assert.assertEquals(baslik, "This is a sample page");
    }

    /*
     * Frame'e geçmeden, ana sayfadayken başlık elementini arar.
     * Beklenen: Element bulunamamalı (frame içi ayrı bir belgedir).
     */
    @Test
    public void frameyeGecmedenBaslikGorunmemeli() {
        Assert.assertTrue(framesPage.anaSayfadanFrameBasligiGorunmuyor(),
                "Frame'e geçilmeden başlık görünmemeliydi");
    }

    /*
     * Önce büyük, sonra küçük frame'in başlığını arka arkaya okur.
     * Beklenen: İkisi de okunabilmeli; bu, ilk frame'den ana sayfaya
     * doğru dönüldüğünü kanıtlar.
     */
    @Test
    public void ikiFrameArkaArkayaOkunabilmeli() {
        // İlk frame'i oku (metot sonunda ana sayfaya dönüyor)
        String buyukBaslik = framesPage.buyukFrameBasliginiAl();

        // Ana sayfaya dönülmeseydi frame2 bulunamaz, burada hata alırdık
        String kucukBaslik = framesPage.kucukFrameBasliginiAl();

        Assert.assertEquals(buyukBaslik, "This is a sample page");
        Assert.assertEquals(kucukBaslik, "This is a sample page");
    }
}