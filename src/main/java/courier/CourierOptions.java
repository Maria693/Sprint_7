package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierOptions {
    public Courier courierDefault() {
        return new Courier("maria", "masha", "Marii");
    }
    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(5), "1993", "masha1");
    }
}
