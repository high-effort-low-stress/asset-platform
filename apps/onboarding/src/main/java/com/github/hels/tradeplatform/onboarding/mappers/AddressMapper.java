package com.github.hels.tradeplatform.onboarding.mappers;

import com.github.hels.tradeplatform.onboarding.dto.domain.AddressDto;
import com.github.hels.tradeplatform.onboarding.dto.domain.ViaCepDto;
import com.github.hels.tradeplatform.onboarding.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toAddressDto(ViaCepDto viaCepDto);

    Address toAddress (AddressDto dto);
}
