package com.MyEvents.service;

import com.MyEvents.model.Event;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.repository.EventRepository;
import com.MyEvents.repository.ParticipantRepository;
import com.MyEvents.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Service
public class RegistrationService {
    /*
    RegistrationService: registering a participant for an event (with validation:
e.g. if there is a place, if not registered twice)
     */

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventService eventService;

    public RegistrationService(RegistrationRepository registrationRepository, EventRepository eventRepository, ParticipantRepository participantRepository, EventService eventService) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.eventService = eventService;
    }

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationService() {

    }
    //RegistrationController:POST /api/events/{eventId}/register/{participantId}

    public Registration registerParticipantToEvent(long eventId, long participantId) {

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("There is no such event of id" + eventId));
        Participant participant = participantRepository.findById(participantId).orElseThrow(() -> new RuntimeException("There is no such participant of id" + participantId));

       if (eventService.checkAvailableSeats(event) <= 0) {
           throw new RuntimeException("There are no places left at" + event.getName());
       };

       if(participant.getRegistrations().stream().anyMatch(a -> a.getEvent().getId().equals(event.getId()))) {
           throw new RuntimeException("Participant of id" + participantId + "is already registered for event of id" + eventId);
       }

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setParticipant(participant);
        registration.setRegistrationDate(String.valueOf(LocalDateTime.now()));
        registrationRepository.save(registration);
        return registration;


    }

    public Registration findById(long id) {
      return registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Ther is no registratio with such id"));
    }

}
