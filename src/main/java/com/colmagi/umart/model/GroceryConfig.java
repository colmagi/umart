package com.colmagi.umart.model;

import com.colmagi.umart.data.AbstractGroceryObject;
import com.colmagi.umart.products.Clothes;
import com.colmagi.umart.products.Vegetable;
import com.colmagi.umart.repository.ItemRepository;
import com.colmagi.umart.enums.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GroceryConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository repository) {
        return args -> {
            Vegetable tomato = new Vegetable(
                    new AbstractGroceryObject(1, "Tomato", Category.VEGETABLE, 5, 1.25, "weight:10")
            );

            Clothes jeans = new Clothes(
                   new AbstractGroceryObject(2, "Jeans", Category.CLOTHES, 3, 30.0, "size:L,sex:M")
            );

            repository.saveAll(List.of(tomato, jeans));
        };
    }
}
