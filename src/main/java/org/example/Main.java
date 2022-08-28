package org.example;

import org.example.cache.EnableCaching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
//@EnableMongoRepositories
@EnableCaching
@EnableR2dbcRepositories
@RestController
public class Main {




    @Autowired
    ItemService itemService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostMapping("/item")
    public ResponseEntity<Mono<Item>>createItem(@RequestBody Item item){

        return new ResponseEntity<Mono<Item>>(itemService.save(item), HttpStatus.CREATED);
    }


    @GetMapping("/item/{itemId}")
    public Mono<Item> findItemFromDB(@PathVariable  Integer itemId){
        return itemService.getItem(itemId);
    }
    @GetMapping("/itemcache/{itemId}")
    public Mono<Item> findItemFromCache(@PathVariable Integer itemId){
        return itemService.getItem_withCache(itemId);
    }

  /*  @GetMapping("/itemcacheaddon/{itemId}")
    public Mono<Item> findItemFromCacheAddon(@PathVariable Integer itemId){
        return itemService.getItem_withAddons(itemId);
    }
*/
}