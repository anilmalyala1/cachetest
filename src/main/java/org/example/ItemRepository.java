package org.example;

//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends ReactiveCrudRepository<Item, Integer> {
}
