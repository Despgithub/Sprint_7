package ru.yandex.practicum.helper;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateOrderResponse;

import static org.apache.http.HttpStatus.SC_CREATED;

public class OrderHelper {
    @Step("Сериализация успешного создания заказа")
    public static CreateOrderResponse createOrderSerialization(Response response) {
        return response.then().statusCode(SC_CREATED).log().all().and().extract().as(CreateOrderResponse.class);
    }
}
