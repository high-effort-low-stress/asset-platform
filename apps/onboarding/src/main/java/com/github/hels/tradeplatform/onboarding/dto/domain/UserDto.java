package com.github.hels.tradeplatform.onboarding.dto.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {
    private Long id;
    private String name;
    private String document;
    private String email;
    @ToString.Exclude
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private boolean isActive;
    private List<AddressDto> addresses;
}
