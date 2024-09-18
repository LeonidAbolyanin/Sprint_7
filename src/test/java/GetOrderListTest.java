import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.order.OrderAssert;
import praktikum.order.OrderStep;

public class GetOrderListTest {
    private OrderStep orderSteps;
    private OrderAssert orderAssertVoid = new OrderAssert();

    @Before
    public void setUp(){
        orderSteps = new OrderStep();
    }

    @DisplayName("Проверка получения списка заказов")
    @Description("Тест на успешное получение списка заказов")
    @Test
    public void testGetOrdersList(){
        ValidatableResponse response = orderSteps.getOrdersList();
        orderAssertVoid.successGetOrdersList(response);
    }
}
