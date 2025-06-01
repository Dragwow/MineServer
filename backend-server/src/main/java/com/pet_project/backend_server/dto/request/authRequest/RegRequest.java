package com.pet_project.backend_server.dto.request.authRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static com.pet_project.backend_server.util.ExceptionUtil.USERNAME_IS_NOT_VALID;
import static com.pet_project.backend_server.util.ExceptionUtil.USER_EMAIL_IS_NOT_VALID;
import static com.pet_project.backend_server.util.ValidatorsUtil.*;

@Getter
@Setter
@Schema(description = "Registration request")
public class RegRequest {

    @NotBlank(message = "Username can't be blank")
    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_IS_NOT_VALID + "Only alphanumeric, hyphens, and underscores are allowed.")
    @Schema(description = "Authentication field: username (unique username)", example = "john_doe")
    private String username;

    @NotBlank(message = "First name can't be blank")
    @Schema(description = "Authentication field: first name", example = "John")
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Schema(description = "Authentication field: last name", example = "Doe")
    private String lastName;

    @NotBlank(message = "Email can't be blank")
    @Pattern(regexp = EMAIL_REGEX, message = USER_EMAIL_IS_NOT_VALID + "Please provide a valid email address.")
    @Schema(description = "Authentication field: email (unique email)", example = "example@email.com")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Schema(description = "Authentication field: password", example = "SecurePassword123!")
    private String password;
}
