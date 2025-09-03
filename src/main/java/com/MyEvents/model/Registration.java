package com.MyEvents.model;


public class Registration {
    private Long id;
    private Event event;
    private Participant participant;
    private String registrationDate;

    public Registration(Long id, Event event, Participant participant, String registrationDate) {
        this.id = id;
        this.event = event;
        this.participant = participant;
        this.registrationDate = registrationDate;
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
