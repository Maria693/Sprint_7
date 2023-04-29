import courier.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierTest {
    private CourierOptions options = new CourierOptions();
    private CourierMethods check;
    private CourierApi step;
    private Courier courier;
    int id;
    @Before
    @Step("create objects for tests")
    public void setUp() {
        step =  new CourierApi();
        courier = options.random();
        check = new CourierMethods();
    }
    @Test
    @Description("Create courier, login, get path and get id for delete")
    public void createCourierSuccessTest() {
        ValidatableResponse responseCreate = step.create(courier);
        check.createdSuccess(responseCreate);
        CourierLogin data = CourierLogin.from(courier);
        ValidatableResponse responseLogin = step.login(data);
        id = responseLogin.extract().path("id");
    }
    @Test
    @Description("Create courier without login")
    public void createCourierFailedTest() {
        courier.setLogin(null);
        ValidatableResponse responseNullLogin = step.create(courier);
        check.createCourierFailed(responseNullLogin);
    }
    @Test
    @Description("Create courier without password")
    public void createCourierFailed2Test() {
        courier.setPassword(null);
        ValidatableResponse responseNullPassword = step.create(courier);
        check.createCourierFailed(responseNullPassword);
    }
    @Test
    @Description("Create courier with same login")
    public void createCourierDouble() {
        step.create(options.courierDefault());
        ValidatableResponse responseDefaultCourier = step.create(options.courierDefault());
        check.createCourierAvailable(responseDefaultCourier);
    }
    @After
    @Step("delete test courier")
    public void deleteCourier() {
        if (id != 0) {
            step.deleteCourier(id);
        }
    }
}
