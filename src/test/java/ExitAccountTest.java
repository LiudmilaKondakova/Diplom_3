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

public class ExitAccountTest {

    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = UrlConfig.BASE_URL;
        driver = WebDriverFactory.get(Config.BROWSER_YANDEX, "login");
//        driver = WebDriverFactory.get(Config.BROWSER_CHROME, "login");
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
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void exitAccountButtonTest() {
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .inputEmail("ffff@ff.ff")
                .inputPassword("123456")
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .clickPersonalAreaButtonWhileAlreadyLogin()
                .clickExitButton()
                .isLoginHeaderVisible();
        assertTrue("Выход из аккаунта не выполнен", isCheckoutOrderButtonVisible);
    }
}
