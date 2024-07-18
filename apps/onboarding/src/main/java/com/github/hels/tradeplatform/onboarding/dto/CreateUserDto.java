package com.github.hels.tradeplatform.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank(message = "a name must be provided.")
        private String name;

        @NotBlank(message = "a CPF must be provided.")
        @CPF(message = "CPF must be valid.")
        private String document;

        @NotBlank(message = "an email must be provided.")
        @Email(message = "Email must be valid.")
        @Pattern(regexp = ".*(\\w+\\.)+\\w+$", message = "Email must be valid.")
        private String email;

        @ToString.Exclude
        @NotBlank(message = "A password must be provided.")
        private String password;

        @NotBlank(message = "A phone number must be provided.")
        @Pattern(regexp = "55\\d{2}9\\d{8}", message = "Phone number must be valid.")
        private String phoneNumber;

        @NotNull(message = "You must provide your birth date.")
        private LocalDate birthDate;

        @NotNull(message = "Address cannot be null")
        private CreateAddressDto address;
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String id;
    }
}
