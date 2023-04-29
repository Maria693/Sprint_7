import order.OrderApi;
import io.restassured.response.ValidatableResponse;
import order.OrderMethods;
import org.junit.Test;


public class GetOrderTest {
    private OrderApi step = new OrderApi();
    private OrderMethods check = new OrderMethods();
    @Test
    public void checkBody() {
        ValidatableResponse response = step.getOrderList();
        check.checkBodyList(response);
    }
}
