package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.model.GetOrdersResponse;

import java.util.List;

import static ru.yandex.practicum.client.OrderApiClient.getOrderList;
import static ru.yandex.practicum.helper.OrderHelper.getOrdersDeserialization;

public class ListOrderTest {

    @DisplayName("Получение списка заказов")
    @Description("Должен вернуться код '200', и вернуться список заказов")
    @Test
    public void listOrderTest() {
        List<GetOrdersResponse> response = getOrdersDeserialization(getOrderList());
        Assert.assertNotNull("Отсутствует список заказов", response);
    }
}
