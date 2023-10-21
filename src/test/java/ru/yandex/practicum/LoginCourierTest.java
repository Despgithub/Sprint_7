package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
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

    CreateCourierRequest data;
    Response response;

    Integer id;

    @DisplayName("Логин курьера")
    @Description("Должен вернуться код '200', и вернуться id")
    @Test
    public void loginCourierTest() {
        data = getRandomCourier();
        createCourierRequest(data);
        response = loginCourierRequest(new LoginCourierRequest(data.getLogin(), data.getPassword()));
        LoginCourierResponse loginCourierResponse = loginCourierResponseDeserialization(response);
        id = loginCourierResponse.getId();
        Assert.assertNotNull("Ответ не содержит id", id);
    }

    @DisplayName("Логин курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutLoginTest() {
        data = getCourierWithoutLogin();
        createCourierRequest(data);
        response = loginCourierRequest(new LoginCourierRequest(data.getLogin(), data.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllDataDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }


    @DisplayName("Логин курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutPasswordTest() {
        data = getCourierWithoutPassword();
        createCourierRequest(data);
        response = loginCourierRequest(new LoginCourierRequest(data.getLogin(), data.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllDataDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }

    @DisplayName("Логин курьера с неверным логином")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithWrongLoginTest() {
        data = getCourierWithoutPassword();
        createCourierRequest(data);
        response = loginCourierRequest(new LoginCourierRequest("0шИ6ка!", data.getPassword()));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithoutAllDataDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", loginCourierErrorResponse.getMessage(), "Недостаточно данных для входа");
    }


    @DisplayName("Логин курьера с неверным паролем")
    @Description("Должен вернуться код '404', и ошибка 'Учетная запись не найдена'")
    @Test
    public void loginCourierWithWrongPasswordTest() {
        data = getCourierWithoutPassword();
        createCourierRequest(data);
        response = loginCourierRequest(new LoginCourierRequest(data.getLogin(), "0шИ6ка!"));
        LoginCourierErrorResponse loginCourierErrorResponse = loginCourierWithWrongPasswordDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", loginCourierErrorResponse.getMessage(), "Учетная запись не найдена");
    }

    @After
    public void tearDown() {
        if (id != null) {
            deleteCourier(id);
        }
    }

}
