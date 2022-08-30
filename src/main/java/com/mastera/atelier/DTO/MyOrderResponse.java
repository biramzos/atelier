package com.mastera.atelier.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class MyOrderResponse {
    private Long id;
    private String name;
    private String phone;
    private String fullname;

    public MyOrderResponse(Long id,
                           String name,
                           String phone,
                           String fullname){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.fullname = fullname;
    }
}
