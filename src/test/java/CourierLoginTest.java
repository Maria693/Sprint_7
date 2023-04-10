import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest extends BaseTest {
    private CourierApi courierApi = new CourierApi();

    @Test
    public void checkCourierLoginResponseBodyTest() {
        Response responseLogin = courierApi.loginCourier();
        System.out.println(responseLogin.body().asString());

    }

    @Test
    public void checkCourierLoginBadPasswordResponseBodyTest() {
        CourierLogin courierLogin = new CourierLogin("mariia", "mashamariia");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post("/api/v1/courier/login");

        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);

        System.out.println(response.body().asString());

    }
}

