package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Actor;
import com.scaler.bookmyshow.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;


    public Actor createActor(String name, int age, String description) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setAge(age);
        actor.setDescription(description);
        return actorRepository.save(actor);
    }
}
