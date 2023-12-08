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

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    private WebDriver driver;
    public UserClient userClient;
    public User user;
    public String accessToken;

    @Before
    public void setUp() {
        user = new User().generateUser();
        userClient = new UserClient();
        RestAssured.baseURI = UrlConfig.BASE_URL;
//        driver = WebDriverFactory.get(Config.BROWSER_YANDEX, "main");
        driver = WebDriverFactory.get(Config.BROWSER_CHROME, "main");
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
    @DisplayName("Проверка, что работает переход к разделам «Булки»")
    public void clickBunsSelectionButtonTest() {
        boolean isBunsSelected = new MainPage(driver)
                .clickSousesSelectionButton()
                .clickBunsSelectionButton()
                .isBunsVisible();
        assertTrue("Булочки не выбраны", isBunsSelected);
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделам «Соусы»")
    public void clickSousesSelectionButtonTest() {
        boolean isSousesSelected = new MainPage(driver)
                .clickSousesSelectionButton()
                .isSousesVisible();
        assertTrue("Соусы не выбраны", isSousesSelected);
    }

    @Test
    @DisplayName("Проверка, что работает переход к разделам «Начинки»")
    public void clickFillingsSelectionButton() {
        boolean isFillingsSelected = new MainPage(driver)
                .clickFillingsSelectionButton()
                .isFillingsVisible();
        assertTrue("Начинки не выбраны", isFillingsSelected);
    }
}
