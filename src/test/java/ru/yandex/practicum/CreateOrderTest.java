package ru.yandex.practicum;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.model.CreateOrderRequest;
import ru.yandex.practicum.model.CreateOrderResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static ru.yandex.practicum.client.OrderApiClient.createOrder;
import static ru.yandex.practicum.helper.OrderHelper.createOrderSerialization;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final CreateOrderRequest request;

    public CreateOrderTest(CreateOrderRequest request) {
        this.request = request;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        Faker faker = new Faker(new Locale("ru"));
        return new Object[][]{
                {new CreateOrderRequest(faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress(), faker.random().nextInt(1, 215), faker.phoneNumber().phoneNumber(), faker.random().nextInt(1, 7), String.valueOf(LocalDate.now()), faker.book().title(), List.of("BLACK", "GREY"))},
                {new CreateOrderRequest(faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress(), faker.random().nextInt(1, 215), faker.phoneNumber().phoneNumber(), faker.random().nextInt(1, 7), String.valueOf(LocalDate.now()), faker.book().title(), List.of("BLACK"))},
                {new CreateOrderRequest(faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress(), faker.random().nextInt(1, 215), faker.phoneNumber().phoneNumber(), faker.random().nextInt(1, 7), String.valueOf(LocalDate.now()), faker.book().title(), List.of("GREY"))},
                {new CreateOrderRequest(faker.name().firstName(), faker.name().lastName(), faker.address().fullAddress(), faker.random().nextInt(1, 215), faker.phoneNumber().phoneNumber(), faker.random().nextInt(1, 7), String.valueOf(LocalDate.now()), faker.book().title(), List.of())}
        };
    }

    @DisplayName("Создание заказа")
    @Description("Должен вернуться код '201', и вернуться track")
    @Test
    public void createOrderTest() {
        CreateOrderResponse response = createOrderSerialization(createOrder(request));
        Assert.assertNotNull("Ответ не содержит track", response.getTrack());
    }
}



