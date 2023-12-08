package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By registrationButton = By.xpath("/html/body/div/div/main/div/form/button");
    private final By incorrectPasswordError = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/p");
    private final By enterButton = By.xpath("/html/body/div/div/main/div/div/p/a");

    public RegistrationPage inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public RegistrationPage inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public RegistrationPage inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new LoginPage(driver);
    }

    public RegistrationPage clickEmailField() {
        driver.findElement(emailField).click();
        return this;
    }

    public LoginPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return new LoginPage(driver);
    }

    public boolean isIncorrectPasswordError() {
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }
}
