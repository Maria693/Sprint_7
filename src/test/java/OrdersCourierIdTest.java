import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isA;

public class OrdersCourierIdTest extends BaseTest {
    private CourierApi courierApi = new CourierApi();

    @Test
    public void checkOrdersResponseBodyTest() {
        Response responseLogin = courierApi.loginCourier();
        responseLogin.then().assertThat().body("id", isA(Integer.class))
                .and()
                .statusCode(200);

        String IdString = responseLogin.body().asString();
        Gson gson = new Gson();
        CourierDelete id = gson.fromJson(IdString, CourierDelete.class);

        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(String.format("/api/v1/orders?courierId=%s", id.getId()));

        response.then().assertThat()
                .statusCode(200);

        System.out.println(response.body().asString());
    }
}
