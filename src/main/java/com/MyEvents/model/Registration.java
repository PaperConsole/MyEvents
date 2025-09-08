package com.MyEvents.model;

import com.MyEvents.dto.RegistrationDto;
import jakarta.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;
    private String registrationDate;

    public Registration() {

    }

    public Registration(Long id, Event event, Participant participant, String registrationDate) {
        this.id = id;
        this.event = event;
        this.participant = participant;
        this.registrationDate = registrationDate;
    }

    public RegistrationDto toDto() {
        return new RegistrationDto(event.getName(), participant.getEmail(), getRegistrationDate());
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
