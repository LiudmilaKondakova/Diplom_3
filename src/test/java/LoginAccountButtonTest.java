import config.Config;
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
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class LoginAccountButtonTest {
    private WebDriver chromeDriver;
    private WebDriver yandexDriver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = Config.URL;
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginToLoginButtonTest() {
        yandexDriver = WebDriverFactory.get(Config.BROWSER_YANDEX, "main");
        chromeDriver = WebDriverFactory.get(Config.BROWSER_CHROME, "main");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .clickToAccountButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginToPersonalAccountButtonTest() {
        yandexDriver = WebDriverFactory.get(Config.BROWSER_YANDEX, "main");
        chromeDriver = WebDriverFactory.get(Config.BROWSER_CHROME, "main");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .clickPersonalAreaButtonWhileIsNotLogin()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterToRegistrationButton() {
        yandexDriver = WebDriverFactory.get(Config.BROWSER_YANDEX, "login");
        chromeDriver = WebDriverFactory.get(Config.BROWSER_CHROME, "login");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .clickToRegistrationButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void enterToPasswordRecoverButton() {
        yandexDriver = WebDriverFactory.get(Config.BROWSER_YANDEX, "login");
        chromeDriver = WebDriverFactory.get(Config.BROWSER_CHROME, "login");
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(yandexDriver != null ? yandexDriver : chromeDriver)
                .clickRecoverPasswordButton()
                .clickEnterButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .clickStellarIsBurgerLogo()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }
}
