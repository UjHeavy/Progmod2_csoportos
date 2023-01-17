package com.example.demo;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.TransformerException;

@RestController
class MessageController {
    @Autowired
    private final ChatRepository repository;

    MessageController(ChatRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]

    //@GetMapping("/messages/cica")
    //List<Entity> all() {
    //    return repository.findAll();
    //}
    XMLReader xml = new XMLReader();

    @GetMapping("/messages")
    List<Entity> all() {
        Entity tempEntity;
        List<Entity> listEntity =new ArrayList<>();

        for (long i = 1; i <= repository.count(); i++) {
            tempEntity = repository.findById(i).orElseThrow();
            //tempEntity.setStatus(MessageStatusEnum.SEEN);
                listEntity.add(tempEntity);
        }
        return listEntity;
    }

    @GetMapping("/messages/users/{getter}")
    @ResponseBody
    List<Entity> all2(@PathVariable String getter) throws TransformerException {
        Entity tempEntity;
        List<Entity> listEntity =new ArrayList<>();

        for (long i = 1; i <= repository.count(); i++) {
            tempEntity = repository.findById(i).orElseThrow();
            if (Objects.equals(tempEntity.getGetter(), getter)) {
                tempEntity.setStatus(MessageStatusEnum.SEEN);
                //xml.save(tempEntity);
                repository.save(tempEntity);
                listEntity.add(tempEntity);
            }
            else if (Objects.equals(tempEntity.getSetter(), getter)){
                repository.save(tempEntity);
                listEntity.add(tempEntity);
            }
        }
        return listEntity;
    }
    // end::get-aggregate-root[]

    @PostMapping("/messages/users/{setter}")
    Entity newMessage(@RequestBody Entity newEntity, @PathVariable String setter) throws TransformerException {
        newEntity.setStatus(MessageStatusEnum.SENT);
        newEntity.setSetter(setter);
        long count = repository.count();
        newEntity.setId(count+1);
        xml.save(newEntity);
        return repository.save(newEntity);
    }

    // Single item

    @GetMapping("/messages/{id}")
    Entity one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @PutMapping("/messages/{id}")
    Entity replaceMessage(@RequestBody Entity newEntity, @PathVariable Long id) {

        return repository.findById(id)
                .map(entity -> {
                    entity.setSetter(newEntity.getSetter());
                    entity.setGetter(newEntity.getGetter());
                    entity.setMessage(newEntity.getMessage());
                    try {
                        xml.save(entity);
                    } catch (TransformerException e) {
                        throw new RuntimeException(e);
                    }
                    return repository.save(entity);
                })
                .orElseGet(() -> {
                    newEntity.setId(id);
                    try {
                        xml.save(newEntity);
                    } catch (TransformerException e) {
                        throw new RuntimeException(e);
                    }
                    return repository.save(newEntity);
                });
    }

    @DeleteMapping("/messages/{id}")
    void deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
