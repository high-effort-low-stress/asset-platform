package com.github.hels.tradeplatform.onboarding.mappers;

import com.github.hels.tradeplatform.onboarding.dto.CreateAddressDto;
import com.github.hels.tradeplatform.onboarding.dto.domain.AddressDto;
import com.github.hels.tradeplatform.onboarding.infrastructure.ViaCep;
import com.github.hels.tradeplatform.onboarding.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toAddressDto(ViaCep viaCep);

    Address toAddress(AddressDto dto);

    AddressDto toAddressDto(CreateAddressDto addressDto);

}
