package com.example.testfirestore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/user")
public class UserController {

    UserService service;

    @PostMapping
    public Mono<User> save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping(path = "/all")
    public Flux<User> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "/test")
    public Mono<Void> test() {
        return service.test();
    }

    @GetMapping("/{name}")
    public Mono<User> getById(@PathVariable String name) {
        return service.findByName(name);
    }

}
