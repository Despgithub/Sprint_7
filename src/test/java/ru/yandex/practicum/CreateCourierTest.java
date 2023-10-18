package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.model.CreateCourierErrorResponse;
import ru.yandex.practicum.model.CreateCourierResponse;

import static ru.yandex.practicum.Steps.CreateCourier.*;
import static ru.yandex.practicum.helper.CourierHelper.*;


public class CreateCourierTest {

    @DisplayName("Создание курьера")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierTest() {
        CreateCourierResponse response = createCourierSerialization(createCourier());
        Assert.assertEquals("Неверный ответ запроса", response.getOk(), true);
    }

    @DisplayName("Создание курьеров с одинаковыми данными")
    @Description("Должен вернуться код '409', и ошибка 'Этот логин уже используется. Попробуйте другой.'")
    @Test
    public void createCourierDoubleCourierTest() {
        CreateCourierErrorResponse response = createDoubleCourierSerialization(createDoubleCourier());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Этот логин уже используется. Попробуйте другой.");
    }


    @DisplayName("Создание курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test()
    public void createCourierWithoutLoginTest() {
        CreateCourierErrorResponse response = createCourierWithoutAllDataSerialization(createCourierWithoutLogin());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для создания учетной записи'")
    @Test
    public void createCourierWithoutPasswordTest() {
        CreateCourierErrorResponse response = createCourierWithoutAllDataSerialization(createCourierWithoutPassword());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Недостаточно данных для создания учетной записи");
    }

    @DisplayName("Создание курьера без имени")
    @Description("Должен вернуться код '201', а в теле сообщения 'ok:true'")
    @Test
    public void createCourierWithoutNameTest() {
        CreateCourierResponse response = createCourierSerialization(createCourierWithoutName());
        Assert.assertEquals("Неверный ответ запроса", response.getOk(), true);
    }

}
