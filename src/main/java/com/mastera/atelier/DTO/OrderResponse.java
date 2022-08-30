package com.mastera.atelier.DTO;

public class OrderResponse {
    private Long id;
    private String name;
    private String phone;
    private UserResponse user;
    public OrderResponse(Long id, String name, String phone, UserResponse user){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.user = user;
    }
}
