package ru.yandex.practicum.helper;


import com.github.javafaker.Faker;
import ru.yandex.practicum.model.CreateCourierRequest;

import java.util.Locale;

public class DataGenerator {

    public static CreateCourierRequest getRandomCourier() {
        Faker faker = new Faker(new Locale("ru"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        return new CreateCourierRequest(login, password, firstName);
    }

    public static CreateCourierRequest getCourierWithoutLogin() {
        Faker faker = new Faker(new Locale("ru"));
        String login = "";
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        return new CreateCourierRequest(login, password, firstName);
    }

    public static CreateCourierRequest getCourierWithoutPassword() {
        Faker faker = new Faker(new Locale("ru"));
        String login = faker.name().username();
        String password = "";
        String firstName = faker.name().firstName();
        return new CreateCourierRequest(login, password, firstName);
    }

    public static CreateCourierRequest getCourierWithoutName() {
        Faker faker = new Faker(new Locale("ru"));
        String login = faker.name().username();
        String password = faker.internet().password();
        String firstName = "";
        return new CreateCourierRequest(login, password, firstName);
    }

}
