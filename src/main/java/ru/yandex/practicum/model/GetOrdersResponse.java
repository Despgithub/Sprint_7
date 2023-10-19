package ru.yandex.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse {
    public Integer id;
    public Object courierId;
    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public Integer rentTime;
    public Date deliveryDate;
    public int track;
    public List<String> color;
    public String comment;
    public Date createdAt;
    public Date updatedAt;
    public int status;
}
