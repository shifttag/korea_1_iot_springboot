package org.example.springbootdeveloper.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
