package com.MyEvents.service;

import com.MyEvents.dto.ParticipantDto;
import com.MyEvents.model.Participant;
import com.MyEvents.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ParticipantDto getParticipantById(long id) {
        return participantRepository.getReferenceById(id).toDto();
    }

    public Long save(ParticipantDto participantDto) {
      Participant participant =  participantRepository.save(participantDto.toParticipant());
      return participant.getId();
    }
}
