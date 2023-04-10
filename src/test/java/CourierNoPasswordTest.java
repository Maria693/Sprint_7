import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierNoPasswordTest extends BaseTest {
    private CourierApi courierApi = new CourierApi();

    @Test
    public void checkCourierResponseWithoutFieldBodyTest() {
        CourierWithoutPassword courierWithoutPassword = new CourierWithoutPassword("mariia", "Marii");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courierWithoutPassword)
                .when()
                .post("/api/v1/courier");

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);

        System.out.println(response.body().asString());

    }
}
