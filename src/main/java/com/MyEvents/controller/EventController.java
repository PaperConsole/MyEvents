package com.MyEvents.controller;

import com.MyEvents.dto.EventDto;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable long id) {
            return ResponseEntity.ok(eventMapper.toEventDto(eventService.findById(id)));
    }

    @GetMapping("")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll().stream().map(eventMapper::toEventDto).toList());
    }

    @PostMapping("")
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) throws URISyntaxException {
        Long uriId = eventService.save(eventMapper.toEvent(eventDto));
        return ResponseEntity.created(new URI("/api/events/" + uriId)).body(eventMapper.toEventDto(eventService.findById(uriId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable long id, @RequestBody EventDto updatedEventDto) {
       Event updatedEvent =  eventService.updateEvent(id, updatedEventDto);
        return ResponseEntity.ok(eventMapper.toEventDto(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //TODO PutMapping
}
