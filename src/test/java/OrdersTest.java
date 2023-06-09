import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import order.OrderApi;
import order.OrderMethods;
import order.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

@RunWith(Parameterized.class)

public class OrdersTest {
    private OrderApi orderApi = new OrderApi();
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;
    private OrderApi step;
    private OrderMethods check;
    int track;
    public OrdersTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "ТестЦветДата: {8}")
    public static Object[][] params() {
        return new Object[][]{
                {"Alina", "Ivanova", "Utrecht, 11 apt.", 4, "+7 981 123 21 34", 5, "2023-06-05", "Test, come back to Utrecht", List.of("BLACK")},
                {"Alina", "Ivanova", "Utrecht, 11 apt.", 4, "+7 981 123 21 34", 5, "2023-06-05", "Test, come back to Utrecht", List.of("GREY")},
                {"Alina", "Ivanova", "Utrecht, 11 apt.", 4, "+7 981 123 21 34", 5, "2023-06-05", "Test, come back to Utrecht", List.of("BLACK", "GREY")},
                {"Alina", "Ivanova", "Utrecht, 11 apt.", 4, "+7 981 123 21 34", 5, "2023-06-05", "Test, come back to Utrecht", List.of()},
        };
    }
    @Test
    @DisplayName("Check orders response body")
    @Description("Test for order creation")
    public void checkOrdersResponseBodyTest() {
        Orders orders = new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
        ValidatableResponse response = orderApi.createOrder(orders);
    }
}

