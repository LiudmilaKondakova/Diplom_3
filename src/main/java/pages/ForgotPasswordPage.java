package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterButton = By.xpath("/html/body/div/div/main/div/div/p/a");

    public LoginPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return new LoginPage(driver);
    }
}
