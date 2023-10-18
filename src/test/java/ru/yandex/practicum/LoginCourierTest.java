package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.model.CreateCourierRequest;
import ru.yandex.practicum.model.LoginCourierErrorResponse;
import ru.yandex.practicum.model.LoginCourierRequest;
import ru.yandex.practicum.model.LoginCourierResponse;

import static ru.yandex.practicum.client.CourierApiClient.createCourierRequest;
import static ru.yandex.practicum.client.CourierApiClient.loginCourierRequest;
import static ru.yandex.practicum.helper.CourierHelper.*;
import static ru.yandex.practicum.helper.DataGenerator.*;

public class LoginCourierTest {
    CreateCourierRequest createData;
    Response response;

    @DisplayName("Логин курьера")
    @Description("Должен вернуться код '200', и вернуться шестизначный id")
    @Test
    public void loginCourierTest() {
        createData = getRandomCourier();
        createCourierRequest(createData);
        response = loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
        LoginCourierResponse loginCourierResponse = loginCourierResponse(response);
        Assert.assertTrue(loginCourierResponse.getId().toString().matches("(\\d{6})"));
    }

    @DisplayName("Логин курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutLoginTest() {
        createData = getCourierWithoutLogin();
        createCourierRequest(createData);
        response = loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllData(response);
        Assert.assertEquals(loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }

    @DisplayName("Логин курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutPasswordTest() {
        createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        response = loginCourierRequest(new LoginCourierRequest(createData.getLogin(), createData.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllData(response);
        Assert.assertEquals(loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }

    @DisplayName("Логин курьера с неверным логином")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithWrongLoginTest() {
        createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        response = loginCourierRequest(new LoginCourierRequest("0шИ6ка!", createData.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllData(response);
        Assert.assertEquals(loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }

    @DisplayName("Логин курьера с неверным паролем")
    @Description("Должен вернуться код '404', и ошибка 'Учетная запись не найдена'")
    @Test
    public void loginCourierWithWrongPasswordTest() {
        createData = getCourierWithoutPassword();
        createCourierRequest(createData);
        response = loginCourierRequest(new LoginCourierRequest(createData.getLogin(), "0шИ6ка!"));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithWrongPassword(response);
        Assert.assertEquals(loginCourierErrorResponse.getMessage(), "Учетная запись не найдена");
    }

}
