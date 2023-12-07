package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By profileHeader = By.xpath("/html/body/div/div/main/div/nav/ul/li[1]/a");
    private final By stellarIsBurgerLogo = By.xpath("/html/body/div/div/header/nav/div/a");
    private final By constructorButton = By.xpath("/html/body/div/div/header/nav/ul/li[1]/a/p");
    private final By exitButton = By.xpath("/html/body/div/div/main/div/nav/ul/li[3]/button");

    public boolean isProfileHeaderVisible() {
        return driver.findElement(profileHeader).isDisplayed();
    }

    public MainPage clickToStellarIsBurgerLogo() {
        driver.findElement(stellarIsBurgerLogo).click();
        return new MainPage(driver);
    }

    public MainPage clickToConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainPage(driver);
    }

    public LoginPage clickExitButton() {
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }
}
