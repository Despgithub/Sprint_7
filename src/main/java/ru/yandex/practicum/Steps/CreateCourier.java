package ru.yandex.practicum.Steps;

import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateCourierRequest;

import static ru.yandex.practicum.client.CourierApiClient.createCourierRequest;
import static ru.yandex.practicum.helper.CourierHelper.createCourierSerialization;
import static ru.yandex.practicum.helper.DataGenerator.*;

public class CreateCourier {

    public static Response createCourier() {
        return createCourierRequest(getRandomCourier());
    }

    public static Response createCourierWithoutLogin() {
        return createCourierRequest(getCourierWithoutLogin());
    }

    public static Response createCourierWithoutPassword() {
        return createCourierRequest(getCourierWithoutPassword());
    }

    public static Response createCourierWithoutName() {
        return createCourierRequest(getCourierWithoutName());
    }

    public static Response createDoubleCourier() {
        CreateCourierRequest data = getRandomCourier();
        Response response = createCourierRequest(data);
        createCourierSerialization(response);
        return createCourierRequest(data);
    }
}
