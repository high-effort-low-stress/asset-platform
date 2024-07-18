package com.github.hels.tradeplatform.onboarding.service;

import com.github.hels.tradeplatform.db.specifications.ApiSpecification;
import com.github.hels.tradeplatform.db.specifications.Input;
import com.github.hels.tradeplatform.db.specifications.Operator;
import com.github.hels.tradeplatform.onboarding.dto.domain.AddressDto;
import com.github.hels.tradeplatform.onboarding.dto.domain.UserDto;
import com.github.hels.tradeplatform.onboarding.exceptions.ApiException;
import com.github.hels.tradeplatform.onboarding.mappers.UserMapper;
import com.github.hels.tradeplatform.onboarding.models.Address;
import com.github.hels.tradeplatform.onboarding.models.User;
import com.github.hels.tradeplatform.onboarding.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final IUserRepository repository;
    private final Clock clock;
    private final AddressService addressService;
    private final UserMapper userMapper;

    public User execute(UserDto userDto) {
        validateLegalAge(userDto.getBirthDate());
        findDuplicates(userDto.getDocument(), userDto.getEmail(), userDto.getPhoneNumber());

        if (Objects.isNull(userDto.getPassword()))
            throw new RuntimeException("Password can't be null.");

        User user = userMapper.toUser(userDto);

        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));

        AddressDto addressDto = userDto.getAddresses().stream().findFirst().orElseThrow();
        Address address = addressService.execute(addressDto);

        address.setUser(user);
        user.setAddresses(List.of(address));

        return repository.save(user);
    }

    private void findDuplicates(String document, String email, String phoneNumber) {
        List<User> users = repository.findAll(buildSpecification(document, email, phoneNumber));

        if (!users.isEmpty())
            throw new ApiException("User already registered.");
    }

    private void validateLegalAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now(clock);

        if (Objects.isNull(birthDate))
            throw new RuntimeException("Birth date can't be null");

        if (Period.between(birthDate, currentDate).getYears() < 18)
            throw new ApiException("User must be 18+ years old");
    }

    private ApiSpecification<User> buildSpecification(String document, String email, String phoneNumber) {
        Input<User> input = new Input<>();
        input
                .addRootField("document", Operator.EQUAL, document)
                .addRootField("email", Operator.EQUAL, email)
                .addRootField("phoneNumber", Operator.EQUAL, phoneNumber)
                .addRootField("isActive", Operator.EQUAL, true);

        return ApiSpecification.<User>builder()
                .input(input)
                .build();
    }

}
