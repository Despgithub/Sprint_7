package ru.yandex.practicum.Steps;

import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateCourierRequest;
import ru.yandex.practicum.model.LoginCourierRequest;

import static ru.yandex.practicum.client.CourierApiClient.createCourierRequest;
import static ru.yandex.practicum.client.CourierApiClient.loginCourierRequest;
import static ru.yandex.practicum.helper.DataGenerator.*;

public class LoginCourier {
    public static Response loginCourier() {
        CreateCourierRequest createData = getRandomCourier();
        createCourierRequest(createData);
        return loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
    }

    public static Response loginCourierWithoutLogin() {
        CreateCourierRequest createData = getCourierWithoutLogin();
        createCourierRequest(createData);
        return loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
    }

    public static Response loginCourierWithoutPassword() {
        CreateCourierRequest createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        return loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
    }

    public static Response loginCourierWithWrongLogin() {
        CreateCourierRequest createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        return loginCourierRequest(new LoginCourierRequest("0шИ6ка!", createData.getPassword()));
    }

    public static Response loginCourierWithWrongPassword() {
        CreateCourierRequest createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        return loginCourierRequest(new LoginCourierRequest(createData.getLogin(), "0шИ6ка!"));
    }

}
