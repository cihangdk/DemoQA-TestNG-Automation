package pages;

import org.openqa.selenium.By;
import utilities.ConfigReader;
import utilities.Driver;

public class FramesPage {

    // Sayfadaki iki iframe'in id'leri
    private final String buyukFrameId = "frame1";
    private final String kucukFrameId = "frame2";

    // İki frame'in içinde de aynı başlık var: <h1 id="sampleHeading">
    private final By frameBasligi = By.id("sampleHeading");

    // Adresi ayar dosyasından alıp sayfayı açar
    public void sayfayiAc() {
        Driver.getdriver().get(ConfigReader.getProperty("framesUrl"));
    }

    // Büyük frame'e girer, başlığı okur, ana sayfaya döner
    public String buyukFrameBasliginiAl() {
        return frameIcindekiBasligiAl(buyukFrameId);
    }

    // Küçük frame'e girer, başlığı okur, ana sayfaya döner
    public String kucukFrameBasliginiAl() {
        return frameIcindekiBasligiAl(kucukFrameId);
    }

    // Frame'e girmeden başlığı arar; bulamazsa true döner
    public boolean anaSayfadanFrameBasligiGorunmuyor() {
        return Driver.getdriver().findElements(frameBasligi).isEmpty();
    }

    // Ortak iş: frame'e gir, metni oku, finally ile her durumda ana sayfaya dön
    private String frameIcindekiBasligiAl(String frameId) {
        Driver.getdriver().switchTo().frame(frameId);
        try {
            return Driver.getdriver().findElement(frameBasligi).getText();
        } finally {
            Driver.getdriver().switchTo().defaultContent();
        }
    }
}