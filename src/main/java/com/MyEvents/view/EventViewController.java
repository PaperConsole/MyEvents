package com.MyEvents.view;

import com.MyEvents.dto.EventDto;
import com.MyEvents.exception.AlreadyRegisteredException;
import com.MyEvents.exception.EventNotFoundException;
import com.MyEvents.exception.ParticipantNotFoundException;
import com.MyEvents.exception.RegistrationFullException;
import com.MyEvents.mapper.EventMapper;
import com.MyEvents.model.Event;
import com.MyEvents.service.EventService;
import com.MyEvents.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashSet;

@Controller
@RequestMapping("/events")
public class EventViewController {


    @Autowired
    EventService eventService;

    @Autowired
    EventMapper eventMapper;

    @Autowired
    RegistrationService registrationService;

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

    @PostMapping("/{id}/register")
    public String register(@PathVariable Long id,
                           @RequestParam long participantId,
                           RedirectAttributes ra) {
        try {
            registrationService.registerParticipantToEvent(id, participantId);
            ra.addAttribute("success", "Registered successfully.");
        } catch (AlreadyRegisteredException e) {
            ra.addAttribute("error", "You are already registered for this event.");
            ra.addAttribute("showForm", "1");
        } catch (RegistrationFullException e) {
            ra.addAttribute("error", "No seats available for this event.");
        } catch (EventNotFoundException | ParticipantNotFoundException e) {
            ra.addAttribute("error", e.getMessage());
        }
        return "redirect:/events/{id}";
    }




    /*
     /events – list of events
● /events/new – event addition form
● /events/{id} – event details + "Sign up" button
     */
}
