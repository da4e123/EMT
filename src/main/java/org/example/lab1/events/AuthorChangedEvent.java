package org.example.lab1.events;

import lombok.Getter;
import org.example.lab1.model.domain.Author;import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}
