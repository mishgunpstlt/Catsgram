package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Указываем, что класс PostService - является бином и его
// нужно добавить в контекст приложения
@Service
public class PostService {
    private final Map<Long, Post> posts = new HashMap<>();

    private final UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Collection<Post> findAll(long from, long size, String sort) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер выборки size должен быть больше нуля");
        }
        return posts.values().stream()
                .skip(from)
                .limit(size)
                .sorted(sort.equalsIgnoreCase("asc")
                        ? Comparator.comparing(Post::getPostDate)
                        : Comparator.comparing(Post::getPostDate).reversed())
                .toList();
    }

    public Post create(Post post) {
        if (post.getDescription() == null || post.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }
        if (userService.findUserById(post.getAuthorId()) != null) {
            post.setId(getNextId());
            post.setPostDate(Instant.now());
            posts.put(post.getId(), post);
        } else {
            throw new ConditionsNotMetException("Автор с id=" + post.getAuthorId() + " не найден");
        }
        return post;
    }

    public Post update(Post newPost) {
        if (newPost.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (posts.containsKey(newPost.getId())) {
            Post oldPost = posts.get(newPost.getId());
            if (newPost.getDescription() == null || newPost.getDescription().isBlank()) {
                throw new ConditionsNotMetException("Описание не может быть пустым");
            }
            oldPost.setDescription(newPost.getDescription());
            return oldPost;
        }
        throw new NotFoundException("Пост с id = " + newPost.getId() + " не найден");
    }

    public Post findPostById(Long id) {
        if (posts.get(id) != null) {
            return posts.get(id);
        } else {
            throw new NotFoundException("Пост с id = " + id + " не найден");
        }
    }

    private long getNextId() {
        long currentMaxId = posts.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}