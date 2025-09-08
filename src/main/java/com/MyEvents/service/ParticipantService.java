package com.MyEvents.service;

import com.MyEvents.dto.ParticipantDto;
import com.MyEvents.mapper.ParticipantMapper;
import com.MyEvents.model.Event;
import com.MyEvents.model.Participant;
import com.MyEvents.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantMapper participantMapper;



    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantDto getParticipantById(long id) {
        return participantMapper.toDto(participantRepository.getReferenceById(id));
    }

    public Long save(ParticipantDto participantDto) {
      Participant participant =  participantRepository.save(participantMapper.toParticipant(participantDto));
      return participant.getId();
    }

    public Participant findByEmailContainingIgnoreCase(String name) {
        return participantRepository.findByEmailContainingIgnoreCase(name.trim()).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Event not found: " + name));
    }
}
