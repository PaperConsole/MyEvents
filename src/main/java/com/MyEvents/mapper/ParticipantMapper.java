package com.MyEvents.mapper;

import com.MyEvents.dto.ParticipantDto;
import com.MyEvents.model.Event;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.service.EventService;
import com.MyEvents.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ParticipantMapper {

    @Autowired
    private RegistrationService registrationService;

    public Participant toParticipant(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setEmail(participantDto.getEmail());
        participant.setName(participantDto.getName());
        Set<Registration> registrations = new HashSet<>();
        for(long id : participantDto.getRegistrationId()) {
            registrations.add(registrationService.findById(id));
        }

        participant.setRegistrations(registrations);
        return participant;
    }

    public ParticipantDto toDto(Participant participant) {

        return new ParticipantDto(participant.getName(), participant.getEmail(),
                participant.getRegistrations().stream().map(Registration::getId).collect(Collectors.toSet()));

    }

}
