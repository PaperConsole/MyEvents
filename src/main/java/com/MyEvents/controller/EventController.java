package com.MyEvents.controller;

import com.MyEvents.dto.EventDto;
import com.MyEvents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    /*EventController (REST):GET /api/events, GET /api/events/{id}, POST, PUT,
DELETE
     */

    @Autowired
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable long id) {
            return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) throws URISyntaxException {
        Long uriId = eventService.save(eventDto);
        return ResponseEntity.created(new URI("/api/events/" + uriId)).body(eventService.findById(uriId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //TODO PutMapping
}
