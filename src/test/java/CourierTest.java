import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierTest extends BaseTest{
    private CourierApi courierApi = new CourierApi();

    @Test
    public void checkCourierResponseBodyTest() {

        Response responseLogin = courierApi.loginCourier();

    }

    @Test
    public void checkCourierDoubleResponseBodyTest() {

        Response response1 = courierApi.createCourier1();

        response1.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);

        System.out.println(response1.body().asString());
    }
}
