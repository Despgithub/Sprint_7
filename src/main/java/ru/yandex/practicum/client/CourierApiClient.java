package ru.yandex.practicum.client;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateCourierRequest;
import ru.yandex.practicum.model.LoginCourierRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex.practicum.config.ConfigApp.*;

public class CourierApiClient extends BaseApiClient {
    @Step("Отправка запроса на создание курьера")
    public static Response createCourierRequest(CreateCourierRequest createCourierRequest) {
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .filter(new AllureRestAssured())
                .post(CREATE_COURIER_URL);
    }

    @Step("Логин курьера")
    public static Response loginCourierRequest(LoginCourierRequest loginCourierRequest) {
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(LOGIN_COURIER_URL);
    }

    @Step("Удаление курьера")
    public static Response deleteCourierRequest(Integer id) {
        return given()
                .spec(getSpec())
                .pathParam("id", id)
                .when()
                .delete(ACCEPT_ORDER_URL + "/{id}");
    }
}
