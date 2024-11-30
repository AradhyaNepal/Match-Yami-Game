package com.a2.pickyami.game.model;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty
    private  String fullName;
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;






}
