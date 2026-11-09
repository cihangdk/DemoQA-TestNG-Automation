package utilities;

import org.testng.annotations.AfterMethod;

// abstract: Bu sınıftan doğrudan nesne oluşturulmaz, sadece test sınıfları tarafından miras alınır
public abstract class TestBase {

    // Her testten sonra çalışır, tarayıcıyı kapatır ki bir sonraki test temiz tarayıcıyla başlasın
    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}