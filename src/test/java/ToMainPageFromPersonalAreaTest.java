import config.Config;
import config.UrlConfig;
import driver.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class ToMainPageFromPersonalAreaTest {
    private WebDriver chromeDriver;
    private WebDriver yandexDriver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = UrlConfig.BASE_URL;
        yandexDriver = WebDriverFactory.get(Config.BROWSER_YANDEX, "login");
        chromeDriver= WebDriverFactory.get(Config.BROWSER_CHROME, "login");
    }

    @After
    public void tearDown() {
        if (yandexDriver != null) {yandexDriver.quit();}
        if (chromeDriver != null) {chromeDriver.quit();}
        if (accessToken != null) userClient.delete(accessTokenExtraction(user));
    }

    public String accessTokenExtraction(User user) {
        ValidatableResponse response = userClient.login(user);
        return response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на «Конструктор»")
    public void exitAccountPageToConstructorButtonTest() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickToConstructorButton()
                .isBunsVisible();
        assertTrue("Переход через кнопку конструктора не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на логотип Stellar Burgers")
    public void exitAccountPageToStellarIsLogo() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickToStellarIsBurgerLogo()
                .isBunsVisible();
        assertTrue("Переход через кнопку конструктора не выполнен", isCheckoutOrderButtonVisible);
    }
}
