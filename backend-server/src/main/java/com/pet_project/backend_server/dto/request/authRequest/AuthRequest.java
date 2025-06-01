package com.pet_project.backend_server.dto.request.authRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.pet_project.backend_server.util.ExceptionUtil.*;
import static com.pet_project.backend_server.util.ValidatorsUtil.*;

@Getter
@Setter
@Schema(description = "Authentication request")
public class AuthRequest {

    @NotBlank(message = "Username can`t be blank")
    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_IS_NOT_VALID)
    @Schema(description = "Authentication field: username (unique username)")
    private String username;

    @NotBlank(message = "Password can`t be blank")
    @Schema(description = "Authentication field: password")
    private String password;
}
