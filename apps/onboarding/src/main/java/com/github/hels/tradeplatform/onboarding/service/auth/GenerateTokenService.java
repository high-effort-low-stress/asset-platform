package com.github.hels.tradeplatform.onboarding.service.auth;

import com.github.hels.tradeplatform.db.specifications.ApiSpecification;
import com.github.hels.tradeplatform.db.specifications.Input;
import com.github.hels.tradeplatform.db.specifications.Operator;
import com.github.hels.tradeplatform.onboarding.dto.output.LoginOutputDto;
import com.github.hels.tradeplatform.onboarding.exceptions.ApiException;
import com.github.hels.tradeplatform.onboarding.mappers.UserMapper;
import com.github.hels.tradeplatform.onboarding.models.User;
import com.github.hels.tradeplatform.onboarding.repository.IUserRepository;
import com.github.hels.tradeplatform.onboarding.http.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateTokenService {
    private final IUserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    public LoginOutputDto execute(String email, String password){

        User user = repository.findAll(buildSpecification(email))
                .stream()
                .findFirst()
                .orElseThrow((() -> new ApiException("Usuário ou senha não correspondem")));

        if (!BCrypt.checkpw(password, user.getPassword())){
            throw new ApiException("Usuário ou senha não correspondem");
        }

        String token = jwtTokenUtil.generateToken(userMapper.toUserDto(user));
        Long expiration = jwtTokenUtil.getExpirationDateFromToken(token).toEpochSecond(ZoneOffset.UTC);
        return new LoginOutputDto(token, expiration);
    }

    private ApiSpecification<User> buildSpecification(String email){
        Input<User> input = new Input<>();
        input.addRootField("email", Operator.EQUAL, email)
                .addRootField("isActive", Operator.EQUAL, true);

        return ApiSpecification.<User>builder()
                .input(input)
                .build();
    }

}