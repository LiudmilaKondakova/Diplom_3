package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterToAccountButton = By.xpath("/html/body/div/div/header/nav/a/p");
    private final By personalAreaButton = By.xpath("/html/body/div/div/header/nav/a");
    private final By checkoutOrderButton = By.xpath(".//section[2]/div/button");
    private final By stellarIsBurgerLogo = By.xpath("/html/body/div/div/header/nav/div/a/svg");
    private final By bunsSelectionButton = By.xpath(".//section[1]/div[1]/div[1]");
    private final By sousesSelectionButton = By.xpath(".//section[1]/div[1]/div[2]");
    private final By fillingsSelectionButton = By.xpath(".//section[1]/div[1]/div[3]");
    private final By bunsSelection = By.xpath(".//section[1]/div[2]/ul[1]");
    private final By sousesSelection = By.xpath(".//section[1]/div[2]/ul[2]");
    private final By fillingsSelection = By.xpath(".//section[1]/div[2]/ul[3]");
    private final By enterButtonNotLogin = By.xpath("/html/body/div/div/main/div/div/p/a");
    private final By enterLoginHeader = By.xpath("/html/body/div/div/main/div/h2");

    public LoginPage clickToAccountButton() {
        driver.findElement(enterToAccountButton).click();
        return new LoginPage(driver);
    }

    public AccountPage clickPersonalAreaButtonWhileAlreadyLogin() {
        driver.findElement(personalAreaButton).click();
        return new AccountPage(driver);
    }

    public LoginPage clickPersonalAreaButtonWhileIsNotLogin() {
        driver.findElement(personalAreaButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickEnterButtonNotLogin(){
        driver.findElement(enterButtonNotLogin).click();
        return new LoginPage(driver);
    }

    public MainPage clickToStellarIsBurgerLogo() {
        driver.findElement(stellarIsBurgerLogo).click();
        return new MainPage(driver);
    }

    public MainPage clickBunsSelectionButton() {
        driver.findElement(bunsSelectionButton).click();
        return new MainPage(driver);
    }

    public MainPage clickSousesSelectionButton() {
        driver.findElement(sousesSelectionButton).click();
        return new MainPage(driver);
    }

    public MainPage clickFillingsSelectionButton() {
        driver.findElement(fillingsSelectionButton).click();
        return new MainPage(driver);
    }

    public boolean isBunsVisible() {
        return driver.findElement(bunsSelection).isDisplayed() && driver.findElement(bunsSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }

    public boolean isSousesVisible() {
        return driver.findElement(sousesSelection).isDisplayed() && driver.findElement(sousesSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }

    public boolean isFillingsVisible() {
        return driver.findElement(fillingsSelection).isDisplayed() && driver.findElement(fillingsSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }


    public boolean isCheckoutOrderButtonVisible() {
        return driver.findElement(checkoutOrderButton).isDisplayed();
    }

    public boolean isLoginHeaderVisible() {
        return driver.findElement(enterLoginHeader).isDisplayed();
    }
}
