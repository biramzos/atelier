package com.mastera.atelier.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
