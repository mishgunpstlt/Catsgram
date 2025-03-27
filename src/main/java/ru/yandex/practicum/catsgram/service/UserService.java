package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();

    public Collection<User> getUsers() {
        return users.values();
    }

    public User postUsers(User user) {
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
        return user;
    }

    public User putUsers(User newUser) {
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
        return newUser;
    }

    public User findUserById(Long id) {
        if (users.get(id) != null) {
            return users.get(id);
        } else {
            return null;
        }
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
