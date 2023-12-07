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
    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = UrlConfig.BASE_URL;
        driver = WebDriverFactory.get(Config.BROWSER_YANDEX, "reg");
        driver = WebDriverFactory.get(Config.BROWSER_CHROME, "reg");
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) userClient.delete(accessTokenExtraction(user));
    }

    public String accessTokenExtraction(User user) {
        ValidatableResponse response = userClient.login(user);
        return response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на «Конструктор»")
    public void exitAccountPageToConstructorButtonTest() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickToConstructorButton()
                .isBunsVisible();
        assertTrue("Переход через кнопку конструктора не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Переход из личного кабинета по клику на логотип Stellar Burgers")
    public void exitAccountPageToStellarIsLogo() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickToStellarIsBurgerLogo()
                .isBunsVisible();
        assertTrue("Переход через кнопку конструктора не выполнен", isCheckoutOrderButtonVisible);
    }
}
