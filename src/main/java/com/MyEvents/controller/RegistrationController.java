package com.MyEvents.controller;

import com.MyEvents.dto.RegistrationDto;
import com.MyEvents.model.Registration;
import com.MyEvents.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/api/events/{eventId}/register/{participantId}")
    public ResponseEntity<RegistrationDto> RegisterAParticipant(@PathVariable long eventId, @PathVariable long participantId) throws URISyntaxException {
        Registration registration = registrationService.registerParticipantToEvent(eventId,participantId);
        return ResponseEntity.created(new URI("/api/registrations/" + registration.getId())).body(registration.toDto());
    }
}