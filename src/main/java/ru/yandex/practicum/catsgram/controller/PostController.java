package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> findAll(@RequestParam(defaultValue = "0") long from,
                                    @RequestParam(defaultValue = "10") long size,
                                    @RequestParam(defaultValue = "desc") String sort) {

        if (!List.of("desc", "asc").contains(sort)) {
            throw new ParameterNotValidException("sort", "параметр sort должен содержать корректное значение");
        }
        if (size <= 0) {
            throw new ParameterNotValidException("size", "параметр size должен быть больше нуля");
        }
        if (from < 0) {
            throw new ParameterNotValidException("from", "параметр from не может быть меньше нуля");
        }
        return postService.findAll(from, size, sort);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }

    @GetMapping("/{id}")
    public Post findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }
}