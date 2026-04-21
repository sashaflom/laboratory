package services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "student@ukma.edu.ua",
            "teacher@ukma.edu.ua",
            "test123@ukma.edu.ua"
    })
    void emailShouldAcceptValidUkma(String email) {
        assertDoesNotThrow(() -> ValidationService.validateEmailValue(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "student@gmail.com",
            "abc",
            "student@ukma",
            "student@ukma.com"
    })
    void emailShouldThrowForInvalid(String email) {
        assertThrows(RuntimeException.class,
                () -> ValidationService.validateEmailValue(email));
    }

    @Test
    void emailShouldThrowForNull() {
        assertThrows(RuntimeException.class,
                () -> ValidationService.validateEmailValue(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+380961234567",
            "380961234567",
            "0987654321"
    })
    void phoneShouldAcceptValid(String phone) {
        assertDoesNotThrow(() -> ValidationService.validatePhoneValue(phone));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+38096abcd567",
            "phone123",
            "12a45",
            "+3809x111111"
    })
    void phoneShouldThrowForInvalid(String phone) {
        assertThrows(RuntimeException.class,
                () -> ValidationService.validatePhoneValue(phone));
    }

    @Test
    void phoneShouldThrowForNull() {
        assertThrows(RuntimeException.class,
                () -> ValidationService.validatePhoneValue(null));
    }
}