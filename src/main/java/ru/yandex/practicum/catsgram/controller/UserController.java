package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> getUsers() {
        return users.values();
    }

    @PostMapping
    public User postUsers(@RequestBody User user) {
        try {
            if (user.getEmail().isEmpty()) {
                throw new ConditionsNotMetException("Имейл должен быть указан");
            }
            for (User item : users.values()) {
                if (item.equals(user)) {
                    throw new DuplicatedDataException("Этот имейл уже используется");
                }
            }
            user.setId(getNextId());
            user.setRegistrationDate(Instant.now());
            users.put(user.getId(), user);
        } catch (ConditionsNotMetException | DuplicatedDataException exp) {
            System.out.println(exp.getMessage());
        }
        return user;
    }

    @PutMapping
    public User putUsers(@RequestBody User newUser) {
        try {
            if (newUser.getId() == null) {
                throw new ConditionsNotMetException("Id должен быть указан");
            }
            for (User item : users.values()) {
                if (item.equals(newUser)) {
                    throw new DuplicatedDataException("Этот имейл уже используется");
                }
            }
            if (newUser.getPassword() != null && newUser.getUsername() != null
                    && newUser.getEmail() != null && newUser.getRegistrationDate() != null) {
                users.put(newUser.getId(), newUser);
            }
        } catch (ConditionsNotMetException | DuplicatedDataException exp) {
            System.out.println(exp.getMessage());
        }
        return newUser;
    }

    // вспомогательный метод для генерации идентификатора нового поста
    private Long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
