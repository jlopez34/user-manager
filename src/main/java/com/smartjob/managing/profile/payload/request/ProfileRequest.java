package com.smartjob.managing.profile.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotBlank
    @Schema(name = "name", example = "Juan Rodriguez", required = true)
    private String name;

    @Pattern(regexp = ".+@.+\\..+", message = "Format email is invalid, should be similar to aaaaaaa@dominio.cl")
    @Schema(name = "email", example = "juan@rodrigue.org", required = true)
    private String email;

    @Pattern(regexp = "((?=.*[a-z])(?=.*d)(?=.*[A-Z]).{6,10})", message = "Password inserted does not compliment with the standard rules")
    @Schema(name = "password", example = "hunter2", required = true)
    private String password;

    @NotBlank
    private List<Phone> phones;
}
