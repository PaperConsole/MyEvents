package com.MyEvents.view;

import com.MyEvents.dto.EventDto;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;

@Controller
@RequestMapping("/events")
public class EventViewController {


    @Autowired
    EventService eventService;

    @Autowired
    EventMapper eventMapper;

    public EventViewController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getAllEvents(Model model) {
        model.addAttribute("eventsDto", eventService.findAll().stream().map(eventMapper::toEventDto).toList());
        return "all-events.html";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id,  Model model) {
        EventDto eventdto = eventMapper.toEventDto(eventService.findById(id));
        model.addAttribute("eventDto", eventdto);
        model.addAttribute("eventId", id);
        return "event.html";
    }


    @GetMapping("/new")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new EventDto("", "", 5, 5, LocalDate.of(2026, 11, 11), new HashSet<>()));
        return "new-event.html";
    }


    @PostMapping("/new")
    public String addBook(@Valid @ModelAttribute EventDto eventDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-event";
        }
        eventService.save(eventMapper.toEvent(eventDto));
        return "redirect:/events";
    }




    /*
     /events – list of events
● /events/new – event addition form
● /events/{id} – event details + "Sign up" button
     */
}
