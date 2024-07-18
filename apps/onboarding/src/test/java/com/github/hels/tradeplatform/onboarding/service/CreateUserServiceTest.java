//package com.github.hels.tradeplatform.onboarding.service;
//
//import com.github.hels.tradeplatform.onboarding.exceptions.ApiException;
//import com.github.hels.tradeplatform.onboarding.models.User;
//import com.github.hels.tradeplatform.onboarding.repository.IAddressRepository;
//import com.github.hels.tradeplatform.onboarding.repository.IUserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.time.Clock;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//class CreateUserServiceTest {
//    private final static LocalDateTime MOCK_DATE = LocalDateTime
//            .of(2023, 8, 20, 20, 20);
//    private final IUserRepository repository = mock(IUserRepository.class);
//    private final Clock clock = Clock.fixed(MOCK_DATE.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
//    private final ViaCepService viaCepService = mock(ViaCepService.class);
//
//    private final CreateUserService service = new CreateUserService(repository, clock, viaCepService);
//
//    @Test
//    @DisplayName("should throw if birth date is null")
//    void shouldThrowIfBirthDateIsNull() {
//        RuntimeException exception = Assertions.assertThrows(
//                RuntimeException.class,
//                () -> service.execute(
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null)
//        );
//
//        Assertions.assertEquals("Birth date can't be null", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("should throw if user age is less than 18 years old")
//    void shouldThrowIfUserAgeIsLessThan18YearsOld() {
//        ApiException exception = Assertions.assertThrows(
//                ApiException.class,
//                () -> service.execute("Caio",
//                        "123456498",
//                        "cadasdasdasrres@gmail.com",
//                        "12345678",
//                        "551234567898",
//                        LocalDate.of(2015, 8, 23),
//                        "26135560")
//        );
//
//        Assertions.assertEquals("User must be 18+ years old", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("should throw if document, phone or email is duplicated")
//    void shouldThrowIfDocumentPhoneOrEmailIsDuplicated() {
//
//        doReturn(List.of(new User())).when(repository).findAll(any(Specification.class));
//
//        ApiException exception = Assertions.assertThrows(
//                ApiException.class,
//                () -> service.execute("Caio",
//                        "123456498",
//                        "cadasdasdasrres@gmail.com",
//                        "12345678",
//                        "551234567898",
//                        LocalDate.of(2000, 8, 23),
//                        "26135560")
//        );
//
//        Assertions.assertEquals("User already registered.", exception.getMessage());
//
//    }
//
//    @Test
//    @DisplayName("should throw password is null")
//    void shouldThrowPasswordIsNull() {
//
//        doReturn(List.of()).when(repository).findAll(any(Specification.class));
//
//        RuntimeException exception = Assertions.assertThrows(
//                RuntimeException.class,
//                () -> service.execute("Caio",
//                        "123456498",
//                        "cadasdasdasrres@gmail.com",
//                        null,
//                        "551234567898",
//                        LocalDate.of(2000, 8, 23),
//                        "26135560")
//        );
//
//        Assertions.assertEquals("Password can't be null.", exception.getMessage());
//
//    }
//
//    @Test
//    @DisplayName("should save user if no errors were found")
//    void shouldSaveUserIfNoErrorsWereFound() {
//
//        doReturn(List.of()).when(repository).findAll(any(Specification.class));
//        doReturn(new User()).when(repository).save(any());
//
//        service.execute("Caio",
//                "123456498",
//                "cadasdasdasrres@gmail.com",
//                "12345678",
//                "551234567898",
//                LocalDate.of(2000, 8, 23),
//                "26135560");
//
//
//        verify(repository, times(1)).save(any());
//    }
//
//}
