import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isA;

public class OrderApi {
    public Response createOrder(Orders orders) {

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(orders)
                .when()
                .post("/api/v1/orders");
        response.then().assertThat().body("track", isA(Integer.class))
                .and()
                .statusCode(201);

        return response;
    }
}
