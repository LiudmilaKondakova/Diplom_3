package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterLoginHeader = By.xpath("/html/body/div/div/main/div/h2");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By enterButton = By.xpath("/html/body/div/div/main/div/form/button");

    private final By toRegistrationButton = By.xpath("/html/body/div/div/main/div/div/p[1]/a");
    private final By recoverPasswordButton = By.xpath("/html/body/div/div/main/div/div/p[2]/a");
    private final By stellarIsBurgerLogo = By.xpath("/html/body/div/div/header/nav/div/a");

    public LoginPage inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public MainPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return new MainPage(driver);
    }

    public RegistrationPage clickToRegistrationButton() {
        driver.findElement(toRegistrationButton).click();
        return new RegistrationPage(driver);
    }

    public ForgotPasswordPage clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
        return new ForgotPasswordPage(driver);
    }

    public MainPage clickStellarIsBurgerLogo() {
        driver.findElement(stellarIsBurgerLogo).click();
        return new MainPage(driver);
    }

    public boolean isLoginHeaderVisible() {
        return driver.findElement(enterLoginHeader).isDisplayed();
    }
}
