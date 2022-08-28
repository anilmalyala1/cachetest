package org.example;

//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
import org.example.cache.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;

@Service
public class ItemService {
   @Autowired
   private ItemRepository repository;

  //  private final LoadingCache<String, Object> cache;


   // @Cacheable("items")
    public Mono<Item> getItem(Integer id) {
        return repository.findById(id);
    }

    public Mono<Item> save(Item item) {
        return repository.save(item);
    }

    @Cacheable(name = "items")
    public Mono<Item> getItem_withCache(Integer id) {
        return repository.findById(id).cache();
    }
/*
    @Cacheable("items")
    public Mono<Item> getItem_withAddons(Integer id) {
        return CacheMono.lookup(cache.asMap(), id)
                .onCacheMissResume(() -> repository.findById(id).cast(Object.class)).cast(Item.class);
    }
*/
}