

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class CardOrder {

    static WebDriver driver;

    @BeforeAll
    static void setUp() {  //метод в котором прописан путь для драйвера
        WebDriverManager.chromedriver().setup();


    }

    @BeforeEach
    void setUp2() {    // запускается перед каждым тестом
        ChromeOptions options = new ChromeOptions();
        /*options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");*/
        driver = new ChromeDriver(options);
    }

    @AfterEach  //после тестов закрыть все окна и ожидать
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldCorrectData(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Евгений Петров-Иванов");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+92776414167");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",text.trim());
    }

    @Test
    public void shuoldInvalidName(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=text]")).sendKeys("John");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+92776414167");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[class=input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",text.trim());

    }

    @Test
    public void shouldMissingNumber(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Евгений Петров-Иванов");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] [class=input__sub]")).getText();
        assertEquals("Поле обязательно для заполнения",text.trim());

    }

    @Test
    public void shouldInvalidNumber(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Евгений Петров-Иванов");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+7927761416");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone] [class=input__sub]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",text.trim());

    }

}
