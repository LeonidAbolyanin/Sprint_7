import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.courier.*;

public class CourierLoginTest {
    int courierId;
    protected CourierGenerator courierGenerateRandomData = new CourierGenerator();
    private CourierData courierInfo;
    protected CourierStepToWrk courierSteps;
    private CourierCreate courierAssertVoid;
    private CourierLog courierLoginCredintals;

    @Before
    @Step("Создание тестовых данных для курьера")
    public void setUp() {
        courierSteps = new CourierStepToWrk();
        courierInfo = courierGenerateRandomData.createCourierWithRandomData();
        courierSteps.createCourier(courierInfo);
        courierLoginCredintals = CourierLog.from(courierInfo);
        courierAssertVoid = new CourierCreate();
    }

    @After
    @Step("Удаление созданного курьера")
    public void cleanData() {
        courierSteps.courierDelete(courierId);
    }

    @DisplayName("Тест на успешное создание курьера")
    @Description("Авторизация с валидными данными")
    @Test
    public void testSuccessCourierLogin() {
        ValidatableResponse courierLogin = courierSteps.courierAuthorization(courierLoginCredintals);
        courierId = courierLogin.extract().path("id");
        courierAssertVoid.successLoginCourierAndTakeId(courierLogin);
    }

    @DisplayName("Тест на ошибку логина курьера при отсутсвии логина и пароля")
    @Description("Авторизация с пустыми данными")
    @Test
    public void testErrorCourierLoginWithEmptyCreds() {
        ValidatableResponse courierLogin = courierSteps.courierAuthorization(new CourierLog("", ""));
        courierAssertVoid.errorLoginCourierWithoutCredentials(courierLogin);
    }

    @DisplayName("Тест на ошибку логина курьера с несуществующей парой логин+пароль")
    @Description("Авторизация с несуществующими данными")
    @Test
    public void testErrorCourierLoginWithDoesNotExistCredintals() {
        ValidatableResponse courierLogin = courierSteps.courierAuthorization(new CourierLog("f", "s"));
        courierAssertVoid.errorLoginCourierWithNotValidCredintals(courierLogin);
    }

    @DisplayName("Тест на ошибку логина курьера с пустым полем логина")
    @Description("Авторизация без логина")
    @Test
    public void testErrorLoginCourierWithEmptyLogin() {
        ValidatableResponse courierLogin = courierSteps.courierAuthorization(new CourierLog("", courierInfo.getPassword()));
        courierAssertVoid.errorLoginCourierWithoutCredentials(courierLogin);
    }

    @DisplayName("Тест на ошибку авторизации курьера с пустым полем пароля")
    @Description("Авторизация с пустым паролем")
    @Test
    public void testErrorLoginCourierWithEmptyPassword() {
        ValidatableResponse courierLogin = courierSteps.courierAuthorization(new CourierLog(courierInfo.getLogin(), ""));
        courierAssertVoid.errorLoginCourierWithoutCredentials(courierLogin);
    }
}
