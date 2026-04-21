package services;

import domain.Access;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.util.LinkedHashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @BeforeEach
    void setUp() {
        UserRepository.getInstance().setMap(new LinkedHashMap<>());
    }

    @Test
    void createNewAndAdd() {
        User user = UserService.createNewAndAdd("nastya", "12345", Access.ADMIN_MASK);

        Optional<User> foundUser = UserService.findByLogin("nastya");

        assertNotNull(user);
        assertTrue(foundUser.isPresent());
        assertEquals("nastya", foundUser.get().getLogin());
    }

    @Test
    void findByLogin() {
        Optional<User> foundUser = UserService.findByLogin("unknown");

        assertTrue(foundUser.isEmpty());
    }

    @Test
    void deleteUser() {
        User user = UserService.createNewAndAdd("deleteMe", "1111", Access.ADMIN_MASK);

        UserService.delete(user);

        assertTrue(UserService.findByLogin("deleteMe").isEmpty());
    }

    @Test
    void blockUserRole() {
        User user = UserService.createNewAndAdd("blockUser", "2222", Access.ADMIN_MASK);

        UserService.block(user);

        assertTrue(user.isBlocked());
    }

    @Test
    void unblockFromUserRole() {
        User user = UserService.createNewAndAdd("unblockUser", "3333", Access.ADMIN_MASK);

        UserService.block(user);
        UserService.unblock(user);

        assertFalse(user.isBlocked());
    }

    @Test
    void checkPasswordWhenCorrect() throws IllegalAccessException {
        User user = UserService.createNewAndAdd("sessionUser", "qwerty", Access.ADMIN_MASK);
        UserService.setSessionUser(user);

        UserService.checkPassword("qwerty");

        assertEquals(Access.roleString(user.getRole()), UserService.getSessionRole());
    }

    @Test
    void checkPasswordWhenIncorrect() {
        User user = UserService.createNewAndAdd("wrongPassUser", "correct", Access.ADMIN_MASK);
        UserService.setSessionUser(user);

        assertThrows(IllegalAccessException.class, () -> UserService.checkPassword("wrong"));
    }

    @Test
    void newLogin() {
        UserService.createNewAndAdd("oldLogin", "1234", Access.ADMIN_MASK);

        UserService.newLogin("oldLogin", "newLogin");

        assertTrue(UserService.findByLogin("oldLogin").isEmpty());
        assertTrue(UserService.findByLogin("newLogin").isPresent());
    }

    @Test
    void setUpUserIfEmpty() {
        UserService.setUpUser();

        Optional<User> admin = UserService.findByLogin("sashaFlom");

        assertTrue(admin.isPresent());
        assertEquals("0123456789", admin.get().getPassword());
    }

}