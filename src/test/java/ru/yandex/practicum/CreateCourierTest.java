package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.model.CreateCourierErrorResponse;
import ru.yandex.practicum.model.CreateCourierRequest;
import ru.yandex.practicum.model.CreateCourierResponse;

import static ru.yandex.practicum.client.CourierApiClient.createCourierRequest;
import static ru.yandex.practicum.helper.CourierHelper.*;
import static ru.yandex.practicum.helper.DataGenerator.*;


public class CreateCourierTest {
    CreateCourierRequest data;
    Response response;

    @DisplayName("Создание курьера")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierTest() {
        data = getRandomCourier();
        response = createCourierRequest(data);
        CreateCourierResponse createCourierResponse = createCourierDeserialization(response);
        Assert.assertEquals("Неверный ответ запроса", createCourierResponse.getOk(), true);
        deleteCourier(data);
    }

    @DisplayName("Создание курьеров с одинаковыми данными")
    @Description("Должен вернуться код '409', и ошибка 'Этот логин уже используется. Попробуйте другой.'")
    @Test
    public void createCourierDoubleCourierTest() {
        data = getRandomCourier();
        response = createCourierRequest(data);
        createCourierDeserialization(response);
        Response responseDouble = createCourierRequest(data);
        CreateCourierErrorResponse courierErrorResponse = createDoubleCourierDeserialization(responseDouble);
        Assert.assertEquals("Неверная ошибка запроса", courierErrorResponse.getMessage(), "Этот логин уже используется. Попробуйте другой.");
        deleteCourier(data);
    }


    @DisplayName("Создание курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test()
    public void createCourierWithoutLoginTest() {
        data = getCourierWithoutLogin();
        response = createCourierRequest(data);
        CreateCourierErrorResponse createCourierErrorResponse = createCourierWithoutAllDataDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", createCourierErrorResponse.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test
    public void createCourierWithoutPasswordTest() {
        data = getCourierWithoutPassword();
        response = createCourierRequest(data);
        CreateCourierErrorResponse courierErrorResponse = createCourierWithoutAllDataDeserialization(response);
        Assert.assertEquals("Неверная ошибка запроса", courierErrorResponse.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без имени")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierWithoutNameTest() {
        data = getCourierWithoutName();
        response = createCourierRequest(data);
        CreateCourierResponse createCourierResponse = createCourierDeserialization(response);
        Assert.assertEquals("Неверный ответ запроса", createCourierResponse.getOk(), true);
        deleteCourier(data);
    }

}
