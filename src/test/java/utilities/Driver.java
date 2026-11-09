package utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {

    // Constructor private: Driver sınıfından nesne oluşturulmasını engeller,
    // driver'a sadece getdriver() metodu üzerinden ulaşılır
    private Driver() {
    }

    // Tüm testlerin kullanacağı tek WebDriver nesnesi
    private static WebDriver driver;

    // Driver'a değer atanmamışsa ayar dosyasındaki tarayıcıyı açar, atanmışsa aynı driver'ı döndürür (Singleton Pattern)
    public static WebDriver getdriver() {
        if (driver == null) {

            // Tarayıcı türünü ve headless ayarını configuration.properties dosyasından okuyoruz
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

            // Ayardaki tarayıcı türüne göre ilgili driver'ı oluşturuyoruz
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("-headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless=new");
                    }
                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new RuntimeException("Desteklenmeyen tarayıcı: " + browser + " (chrome, firefox veya edge yazın)");
            }

            // Headless modda ekran olmadığı için pencere boyutunu elle veriyoruz, normal modda tam ekran yapıyoruz
            if (headless) {
                driver.manage().window().setSize(new Dimension(1920, 1080));
            } else {
                driver.manage().window().maximize();
            }

            // Implicit wait süresini ayar dosyasından okuyoruz
            long implicitWait = Long.parseLong(ConfigReader.getProperty("implicitWait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        }
        return driver;
    }

    // Driver açıksa tarayıcıyı kapatır ve bir sonraki test için driver'ı sıfırlar
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}