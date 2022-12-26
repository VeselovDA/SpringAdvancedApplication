package ru.education.springadvancedapplication.persistance.model;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PreDestroy;
import java.util.UUID;

@Getter
@Setter
public class Session {
    private UUID uuid=UUID.randomUUID();

    @PreDestroy
    void preDestroy(){
        System.out.println("Конец сесии "+this.getUuid());
    }
}
