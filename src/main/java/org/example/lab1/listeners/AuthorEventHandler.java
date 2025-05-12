package org.example.lab1.listeners;

import org.example.lab1.events.AuthorChangedEvent;
import org.example.lab1.events.AuthorCreatedEvent;
import org.example.lab1.events.AuthorDeletedEvent;
import org.example.lab1.service.application.CountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandler {

    private final CountryApplicationService countryApplicationService;


    public AuthorEventHandler(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event){
        this.countryApplicationService.refreshMaterializedView();
    }

    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event){
        this.countryApplicationService.refreshMaterializedView();
    }

    @EventListener
    public void onAuthorChanged(AuthorChangedEvent authorChangedEvent){
        this.countryApplicationService.refreshMaterializedView();;
    }
}
