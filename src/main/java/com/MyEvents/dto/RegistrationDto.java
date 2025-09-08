package com.MyEvents.dto;

import com.MyEvents.model.Event;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.service.EventService;
import com.MyEvents.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;


public class RegistrationDto {

    private String eventName;
    private String participantEmail;
    private String registrationDate;

    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    public RegistrationDto(String eventName, String participantEmail, String registrationDate) {
        this.eventName = eventName;
        this.participantEmail = participantEmail;
        this.registrationDate = registrationDate;
    }

    public Registration toRegistration () {
        Registration registration = new Registration();
        registration.setRegistrationDate(registrationDate);
        registration.setEvent(eventService.findByNameContainingIgnoreCase(eventName));
        registration.setParticipant(participantService.findByEmailContainingIgnoreCase(participantEmail));
        return registration;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
