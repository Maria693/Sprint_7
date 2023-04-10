import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.apache.http.HttpStatus.*;

public class CourierApi {
    public Response createCourier() {
        Courier courier = new Courier("mariia", "masha", "Marii");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");

        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
        return response;
    }
    public Response createCourier1() {
        Courier courier = new Courier("mariia", "masha", "Marii");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        return response;
    }

    public Response loginCourier() {
        CourierLogin courierLogin = new CourierLogin("mariia", "masha");

        Response responseLogin = given()
                .header("Content-type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post("/api/v1/courier/login");
        responseLogin.then().assertThat().body("id", isA(Integer.class))
                .and()
                .statusCode(SC_OK);

        return responseLogin;
    }

    public Response deleteCourierById(String courierId) {
        Response responseDelete = given()
                .header("Content-type", "application/json")
                .when()
                .delete(String.format("/api/v1/courier/%s", courierId));
        responseDelete.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_OK);

        return responseDelete;
    }
}
