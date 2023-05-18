package Controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverController {
    public DriverController(){}
    public WebDriver Driver()
    {
        System.out.println("---- Instanciando o Driver ----");

        System.setProperty("webdriver.chrome.driver", "src\\test\\driver\\chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriver driver = new ChromeDriver();

        return driver;
    }
}
