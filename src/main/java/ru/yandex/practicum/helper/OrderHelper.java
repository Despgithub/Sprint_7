package ru.yandex.practicum.helper;

import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateOrderResponse;
import ru.yandex.practicum.model.GetOrdersResponse;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class OrderHelper {
    public static CreateOrderResponse createOrderDeserialization(Response response) {
        return response.then().statusCode(SC_CREATED).log().all().and().extract().as(CreateOrderResponse.class);
    }

    public static List<GetOrdersResponse> getOrdersDeserialization(Response response) {
        return response.then().statusCode(SC_OK).log().all().and().extract().body().jsonPath().getList("orders", GetOrdersResponse.class);
    }
}
