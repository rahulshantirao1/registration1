package com.webAppApi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDto {
    @NotNull
    @Size(min = 2,message = "min 2 characters")
    private String name;
    @Email
    private String email;
    @Size(min = 10,max=10,message = "Should be 10 digits")
    private String mobile;
}
