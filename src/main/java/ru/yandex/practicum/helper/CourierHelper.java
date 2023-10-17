package ru.yandex.practicum.helper;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateCourierErrorResponse;
import ru.yandex.practicum.model.CreateCourierResponse;

import static org.apache.http.HttpStatus.*;

public class CourierHelper {

    @Step("Сериализация успешного создания курьера")
    public static CreateCourierResponse createCourier(Response response) {
        return response.then().statusCode(SC_CREATED).log().all().and().extract().as(CreateCourierResponse.class);
    }

    @Step("Сериализация ошибки повторного создания курьера")
    public static CreateCourierErrorResponse createDoubleCourier(Response response) {
        return response.then().statusCode(SC_CONFLICT).log().all().and().extract().as(CreateCourierErrorResponse.class);
    }

    @Step("Сериализация ошибки создания курьера без логина или пароля")
    public static CreateCourierErrorResponse createCourierWithoutAllData(Response response) {
        return response.then().statusCode(SC_BAD_REQUEST).log().all().and().extract().as(CreateCourierErrorResponse.class);
    }
}
