package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ChatRepository repository) {
        XMLReader xml = new XMLReader();
        //System.out.println(xml.load().size());
        return args -> {
            //repository.saveAll(xml.load());

            for (int i = 0; i < xml.load().size(); i++){
                repository.save(xml.load().get(i));
            }
            //repository.save(new Entity("cica", "mica", "meow", MessageStatusEnum.SENT));
            //repository.save(new Entity("mica", "cucu", "MEOW", MessageStatusEnum.SENT));
        };
    }
}