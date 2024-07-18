package com.github.hels.tradeplatform.onboarding.dto.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    private Long id;
    private String zipCode;
    private String uf;
    private String city;
    private String streetName;
    private String district;
    private String streetNumber;
    private String complement;
}
