package com.umart.mdbspringboot.model;

import com.umart.mdbspringboot.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroceryConfig {

    @Bean
    CommandLineRunner commandLineRunner(ItemRepository repository) {
        return args -> {
            Vegetable tomato = new Vegetable(
              "tomato", "tomato", 5, "Vegatable", 5, 0.56
            );

            Clothes jeans = new Clothes(
                    "jeans", "Levi Jeans", 1, "Clothes", 70, "Large",true
            );

            //repository.saveAll(List.of(tomato, jeans));
        };
    }
}
