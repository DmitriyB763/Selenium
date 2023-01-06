

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class CardOrder {

    static WebDriver driver;

    @BeforeAll
    static void setUp() {  //метод в котором прописан путь для драйвера
        System.setProperty("webdriver.chrome.driver", "C:\\idea\\Selenium__web\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void setUp2() {
        driver = new ChromeDriver();
    }

    @AfterEach  //после тестов закрыть все окна и ожидать
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldCorrectData(){
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Москва");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+92776414167");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",text.trim());


    }

}
