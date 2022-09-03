package com.mastera.atelier.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String name;
    private String phone;
    private String date;
    private UserResponse user;
    public OrderResponse(Long id, String name, String phone, String date, UserResponse user){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.user = user;
    }
}
