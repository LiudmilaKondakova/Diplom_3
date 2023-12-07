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
import pages.MainPage;
import pages.RegistrationPage;

import static junit.framework.TestCase.assertTrue;

public class RegistrationTest {
    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    private static final String INVALID_PASSWORD = "123";

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
    @DisplayName("Регистрация с корректными данными")
    public void correctRegistrationTest() {
        boolean isLoginHeaderVisible = new RegistrationPage(driver)
                .inputEmail(user.getEmail())
                .inputName(user.getName())
                .inputPassword(user.getPassword())
                .clickRegistrationButton()
                .isLoginHeaderVisible();
        assertTrue("Регистрация не выполнена", isLoginHeaderVisible);
    }

    @Test
    @DisplayName("Проверка регистрации с паролем менее 6 символов")
    public void passwordLessThenSixSymbolsRegistrationTest() {
        user.setPassword(INVALID_PASSWORD);
        boolean isPasswordErrorVisible = new RegistrationPage(driver)
                .inputEmail(user.getEmail())
                .inputName(user.getName())
                .inputPassword(user.getPassword())
                .clickEmailField()
                .isIncorrectPasswordError();
        assertTrue("Нет ошибки, что пароль некорректен" +
                " и регистрация прошла успешно", isPasswordErrorVisible);
    }
}
