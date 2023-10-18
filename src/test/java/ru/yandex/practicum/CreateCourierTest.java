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
    Response response;

    @DisplayName("Создание курьера")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierTest() {
        response = createCourierRequest(getRandomCourier());
        CreateCourierResponse courierResponse = createCourier(response);
        Assert.assertEquals("Неверный ответ запроса", courierResponse.getOk(), true);
    }

    @DisplayName("Создание курьеров с одинаковыми данными")
    @Description("Должен вернуться код '409', и ошибка 'Этот логин уже используется. Попробуйте другой.'")
    @Test
    public void createCourierDoubleCourierTest() {
        CreateCourierRequest data = getRandomCourier();
        response = createCourierRequest(data);
        createCourier(response);
        Response responseDouble = createCourierRequest(data);
        CreateCourierErrorResponse createCourierErrorResponse = createDoubleCourier(responseDouble);
        Assert.assertEquals("Неверная ошибка запроса", createCourierErrorResponse.getMessage(), "Этот логин уже используется. Попробуйте другой.");
    }

    @DisplayName("Создание курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test()
    public void createCourierWithoutLoginTest() {
        response = createCourierRequest(getCourierWithoutLogin());
        CreateCourierErrorResponse createCourierErrorResponse = createCourierWithoutAllData(response);
        Assert.assertEquals("Неверная ошибка запроса", createCourierErrorResponse.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test
    public void createCourierWithoutPasswordTest() {
        response = createCourierRequest(getCourierWithoutPassword());
        CreateCourierErrorResponse createCourierErrorResponse = createCourierWithoutAllData(response);
        Assert.assertEquals("Неверная ошибка запроса", createCourierErrorResponse.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без имени")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierWithoutNameTest() {
        response = createCourierRequest(getCourierWithoutName());
        CreateCourierResponse courierResponse = createCourier(response);
        Assert.assertEquals("Неверный ответ запроса", courierResponse.getOk(), true);
    }

}
