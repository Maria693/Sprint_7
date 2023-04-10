import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    private CourierApi courierApi = new CourierApi();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response = courierApi.createCourier();
    }

    @After
    public void tearDown() {
        Response responseLogin = courierApi.loginCourier();

        String IdString = responseLogin.body().asString();
        Gson gson = new Gson();
        CourierDelete id = gson.fromJson(IdString, CourierDelete.class);

        Response responseDelete = courierApi.deleteCourierById(id.getId());
    }
}