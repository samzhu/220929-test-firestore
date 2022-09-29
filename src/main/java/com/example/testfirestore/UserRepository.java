package com.example.testfirestore;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User>{

    Mono<User> findByName(String name);
    
}
