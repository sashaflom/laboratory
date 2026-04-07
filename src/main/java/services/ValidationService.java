package services;

import annotations.NotBlank;
import annotations.Phone;
import annotations.UkmaEmail;

import java.lang.reflect.Field;

public class ValidationService {
    public static void validate(Object obj) {
        Class<?> currentClass = obj.getClass();

        while (currentClass != null) {
            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                try {
                    Object value = field.get(obj);

                    if (field.isAnnotationPresent(NotBlank.class)) {
                        if (value == null || value.toString().trim().isEmpty()) {
                            throw new RuntimeException("Помилка! Поле " + field.getName() + " не може бути порожнім.");
                        }
                    }

                    if (field.isAnnotationPresent(UkmaEmail.class)) {
                        if (value == null) {
                            throw new RuntimeException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
                        }

                        String email = value.toString().trim();
                        if (!email.endsWith("@ukma.edu.ua")) {
                            throw new RuntimeException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
                        }
                    }

                    if (field.isAnnotationPresent(Phone.class)) {
                        if (value == null) {
                            throw new RuntimeException("Помилка! Некоректний формат номеру телефону.");
                        }

                        String phone = value.toString().trim();

                        if (phone.length() < 10 || phone.length() > 13) {
                            throw new RuntimeException("Помилка! Довжина телефону має бути від 10 до 13 символів.");
                        }

                        int index = phone.startsWith("+") ? 1 : 0;
                        for (int i = index; i < phone.length(); i++) {
                            if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                                throw new RuntimeException("Помилка! Некоректний формат номеру телефону.");
                            }
                        }
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Помилка доступу до поля " + field.getName(), e);
                }
            }

            currentClass = currentClass.getSuperclass();
        }
    }
    public static void validateEmailValue(String email) {
        if (email == null) {
            throw new RuntimeException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
        }

        String trimmed = email.trim();
        if (!trimmed.endsWith("@ukma.edu.ua")) {
            throw new RuntimeException("Помилка! Email має бути формату xxx@ukma.edu.ua.");
        }
    }

    public static void validatePhoneValue(String phone) {
        if (phone == null) {
            throw new RuntimeException("Помилка! Некоректний формат номеру телефону.");
        }

        String trimmed = phone.trim();

        int index = trimmed.startsWith("+") ? 1 : 0;
        for (int i = index; i < trimmed.length(); i++) {
            if (trimmed.charAt(i) < '0' || trimmed.charAt(i) > '9') {
                throw new RuntimeException("Помилка! Некоректний формат номеру телефону.");
            }
        }
    }
}