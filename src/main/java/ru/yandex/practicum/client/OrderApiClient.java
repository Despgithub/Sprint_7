package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.model.OrderRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.config.ConfigApp.CREATE_ORDER_URL;
import static ru.yandex.practicum.config.ConfigApp.LIST_ORDERS_URL;

public class OrderApiClient extends BaseApiClient {
    @Step("Создание заказа")
    public static Response createOrder(OrderRequest orderRequest) {
        return given()
                .spec(getSpec())
                .body(orderRequest)
                .when()
                .post(CREATE_ORDER_URL);
    }

    @Step("Получение списка заказов")
    public static Response getOrderList() {
        return given()
                .spec(getSpec())
                .when()
                .get(LIST_ORDERS_URL);
    }
}
