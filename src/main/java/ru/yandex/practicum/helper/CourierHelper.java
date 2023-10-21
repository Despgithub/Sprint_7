package ru.yandex.practicum.helper;

import io.restassured.response.Response;
import ru.yandex.practicum.model.CreateCourierErrorResponse;
import ru.yandex.practicum.model.CreateCourierResponse;
import ru.yandex.practicum.model.LoginCourierErrorResponse;
import ru.yandex.practicum.model.LoginCourierResponse;

import static org.apache.http.HttpStatus.*;
import static ru.yandex.practicum.client.CourierApiClient.deleteCourierRequest;

public class CourierHelper {

    public static CreateCourierResponse createCourierDeserialization(Response response) {
        return response.then().statusCode(SC_CREATED).log().all().and().extract().as(CreateCourierResponse.class);
    }

    public static CreateCourierErrorResponse createDoubleCourierDeserialization(Response response) {
        return response.then().statusCode(SC_CONFLICT).log().all().and().extract().as(CreateCourierErrorResponse.class);
    }

    public static CreateCourierErrorResponse createCourierWithoutAllDataDeserialization(Response response) {
        return response.then().statusCode(SC_BAD_REQUEST).log().all().and().extract().as(CreateCourierErrorResponse.class);
    }

    public static LoginCourierResponse loginCourierResponseDeserialization(Response response) {
        return response.then().statusCode(SC_OK).log().all().and().extract().as(LoginCourierResponse.class);
    }

    public static LoginCourierErrorResponse loginCourierWithoutAllDataDeserialization(Response response) {
        return response.then().statusCode(SC_BAD_REQUEST).log().all().and().extract().as(LoginCourierErrorResponse.class);
    }

    public static LoginCourierErrorResponse loginCourierWithWrongPasswordDeserialization(Response response) {
        return response.then().statusCode(SC_NOT_FOUND).log().all().and().extract().as(LoginCourierErrorResponse.class);
    }

    public static void deleteCourier(Integer id) {
        deleteCourierRequest(id).then().statusCode(SC_OK);
    }

}
