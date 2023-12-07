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
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class LoginAccountButtonTest {
    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = Config.URL;
        driver = WebDriverFactory.get(Config.BROWSER_YANDEX, "reg");
//        driver = WebDriverFactory.get(Config.BROWSER_CHROME, "reg");
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginToLoginButtonTest() {
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(driver)
                .clickToAccountButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginToPersonalAccountButtonTest() {
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new MainPage(driver)
                .clickPersonalAreaButtonWhileIsNotLogin()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void enterToRegistrationButton() {
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .clickStellarIsBurgerLogo()
                .clickToAccountButton()
                .clickToRegistrationButton()
                .clickEnterButtonNotLogin()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void enterToPasswordRecoverButton() {
        userClient.create(user);
        boolean isCheckoutOrderButtonVisible = new LoginPage(driver)
                .clickStellarIsBurgerLogo()
                .clickToAccountButton()
                .clickRecoverPasswordButton()
                .clickEnterButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPassword())
                .clickEnterButton()
                .isCheckoutOrderButtonVisible();
        assertTrue("Вход в аккаунт не выполнен", isCheckoutOrderButtonVisible);
    }
}
