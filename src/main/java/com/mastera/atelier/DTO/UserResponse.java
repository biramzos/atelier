package com.mastera.atelier.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String username;
    private String fullname;
    public UserResponse(String username, String fullname){
        this.username = username;
        this.fullname = fullname;
    }
}
