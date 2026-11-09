package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // Ayar dosyasındaki anahtar=değer çiftlerini tutan nesne
    private static Properties properties;

    // static blok: Sınıf ilk kullanıldığında bir kez çalışır ve ayar dosyasını yükler
    static {
        String dosyaYolu = "configuration.properties";
        try (FileInputStream fis = new FileInputStream(dosyaYolu)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Ayar dosyası okunamadı: " + dosyaYolu, e);
        }
    }

    // Constructor private: ConfigReader'dan nesne oluşturulmasını engeller
    private ConfigReader() {
    }

    // Verilen anahtarın değerini döndürür, anahtar dosyada yoksa açıklayıcı hata verir
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("configuration.properties dosyasında '" + key + "' anahtarı bulunamadı");
        }
        return value.trim();
    }
}