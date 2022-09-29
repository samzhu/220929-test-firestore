package com.example.testfirestore;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.cloud.spring.data.firestore.transaction.ReactiveFirestoreTransactionManager;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository repository;

    public Flux<User> getAll() {
        log.info("讀取全部");
        return repository.findAll();
    }

    @Cacheable(cacheNames = "users", key = "#name")
    public Mono<User> findByName(String name) {
        log.info("找尋 {}", name);
        return repository.findByName(name);
    }

    @Transactional
    public Mono<User> save(User user) {
        System.out.println(user.getName());
        return repository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Void> test() {
        return repository
                .findAll()
                .flatMap(
                        a -> {
                            log.info("user name:{}, age:{}", a.getName(), a.getAge());
                            a.setAge(a.getAge() - 1);
                            if (a.getAge() <= 0) {
                                throw new RuntimeException("未成年");
                            }
                            return repository.save(a);
                        })
                .then();
    }
}
