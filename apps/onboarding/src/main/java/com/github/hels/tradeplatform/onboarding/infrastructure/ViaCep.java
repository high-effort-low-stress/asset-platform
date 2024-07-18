package com.github.hels.tradeplatform.onboarding.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViaCep {

    @JsonProperty(value = "cep")
    private String zipCode;

    @JsonProperty(value = "logradouro")
    private String StreetName;

    @JsonProperty(value = "bairro")
    private String district;

    @JsonProperty(value = "localidade")
    private String city;

    @JsonProperty(value = "uf")
    private String uf;

}
