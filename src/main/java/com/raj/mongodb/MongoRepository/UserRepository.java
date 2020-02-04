package com.raj.mongodb.MongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.raj.mongodb.document.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {
}