package ru.yandex.practicum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.model.LoginCourierErrorResponse;
import ru.yandex.practicum.model.LoginCourierResponse;

import static ru.yandex.practicum.Steps.LoginCourier.*;
import static ru.yandex.practicum.helper.CourierHelper.*;

public class LoginCourierTest {

    @DisplayName("Логин курьера")
    @Description("Должен вернуться код '200', и вернуться id")
    @Test
    public void loginCourierTest() {
        LoginCourierResponse response = loginCourierResponseSerialization(loginCourier());
        Assert.assertNotNull("Ответ не содержит id", response.getId());
    }

    @DisplayName("Логин курьера без логина")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutLoginTest() {
        LoginCourierErrorResponse response = loginCourierWithoutAllDataSerialization(loginCourierWithoutLogin());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Недостаточно данных для входа");
    }


    @DisplayName("Логин курьера без пароля")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithoutPasswordTest() {
        LoginCourierErrorResponse response = loginCourierWithoutAllDataSerialization(loginCourierWithoutPassword());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Недостаточно данных для входа");
    }

    @DisplayName("Логин курьера с неверным логином")
    @Description("Должен вернуться код '400', и ошибка 'Недостаточно данных для входа'")
    @Test
    public void loginCourierWithWrongLoginTest() {
        LoginCourierErrorResponse response = loginCourierWithoutAllDataSerialization(loginCourierWithWrongLogin());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Недостаточно данных для входа");
    }


    @DisplayName("Логин курьера с неверным паролем")
    @Description("Должен вернуться код '404', и ошибка 'Учетная запись не найдена'")
    @Test
    public void loginCourierWithWrongPasswordTest() {
        LoginCourierErrorResponse response = loginCourierWithWrongPasswordSerialization(loginCourierWithWrongPassword());
        Assert.assertEquals("Неверная ошибка запроса", response.getMessage(), "Учетная запись не найдена");
    }

}
