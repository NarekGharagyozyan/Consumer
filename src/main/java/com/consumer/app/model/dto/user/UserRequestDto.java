package com.consumer.app.model.dto.user;

import com.consumer.app.util.constants.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {

    @NotBlank
    private String name;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    private Integer age;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Gender gender;
}
