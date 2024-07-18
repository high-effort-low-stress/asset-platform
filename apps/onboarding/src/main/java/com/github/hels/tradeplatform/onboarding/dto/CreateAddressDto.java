package com.github.hels.tradeplatform.onboarding.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateAddressDto {
    private String zipCode;
    private String uf;
    private String city;
    private String streetName;
    private String district;
    private String streetNumber;
    private String complement;
}
