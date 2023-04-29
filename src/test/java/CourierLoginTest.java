import courier.*;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {
    private CourierOptions options = new CourierOptions();
    private CourierMethods check;
    private CourierApi step;
    private Courier courier;
    private CourierLogin data;
    int id;

    @Before
    @Step("create objects for tests")
    public void setUp() {
        step = new CourierApi();
        courier = options.random();
        step.create(courier);
        data = CourierLogin.from(courier);
        check = new CourierMethods();
    }
    @Test
    @Step("login with correct values")
    public void loginSuccessFullyTest() {
        ValidatableResponse responseLogin = step.login(data);
        check.loginSuccess(responseLogin);
        id = responseLogin.extract().path("id");
    }
    @Test
    @Step("login without login and check message error")
    public void loginWithotLoginTest() {
        CourierLogin courierDataWithoutLogin = new CourierLogin("", courier.getPassword());
        ValidatableResponse responseMessageError = step.login(courierDataWithoutLogin);
        check.loginCourierWithoutLogin(responseMessageError);
    }
    @Test
    @Step("login without password and check message error")
    public void loginWithoutPasswordTest() {
        CourierLogin courierDataWithoutPassword = new CourierLogin(courier.getLogin(), "");
        ValidatableResponse responseMessageError = step.login(courierDataWithoutPassword);
        check.loginCourierWithoutLogin(responseMessageError);
    }
    @Test
    @Step ("Check message after login courier with incorrect password")
    public void loginWithNotCorrectPassword() {
        CourierLogin courierDataHere = new CourierLogin(data.getLogin(), "0000");
        ValidatableResponse responseMessageError = step.login(courierDataHere);
        check.loginCourierWithFalseValues(responseMessageError);
    }
    @After
    @Step ("delete test couriers")
    public void deleteCourier(){
        if (id != 0) {
            step.deleteCourier(id);
        }
    }
}

