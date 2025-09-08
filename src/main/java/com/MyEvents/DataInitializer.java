package com.MyEvents;

import com.MyEvents.model.Event;
import com.MyEvents.model.Location;
import com.MyEvents.model.Participant;
import com.MyEvents.model.Registration;
import com.MyEvents.repository.EventRepository;
import com.MyEvents.repository.LocationRepository;
import com.MyEvents.repository.ParticipantRepository;
import com.MyEvents.repository.RegistrationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public DataInitializer(EventRepository eventRepository,
                           LocationRepository locationRepository,
                           ParticipantRepository participantRepository,
                           RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Location loc = new Location();
        loc.setName("Main Hall");
        loc.setCity("Warsaw");
        loc.setAddress("Main Street 1");
        locationRepository.save(loc);

        Event event = new Event();
        event.setName("Spring Boot Workshop");
        event.setDescription("Learn the basics of Spring Boot");
        event.setCapacity(50);
        event.setLocation(loc);
        eventRepository.save(event);

        Event event2 = new Event();
        event2.setName("Salsa dance");
        event2.setDescription("Learn the basics of salsa");
        event2.setCapacity(2);
        event2.setLocation(loc);
        eventRepository.save(event2);

        Participant p = new Participant();
        p.setName("Anna Kowalska");
        p.setEmail("anna@example.com");
        participantRepository.save(p);

        Participant p2 = new Participant();
        p2.setName("Tomasz Kowalski");
        p2.setEmail("tomasz@example.com");
        participantRepository.save(p2);

        Registration reg = new Registration();
        reg.setEvent(event);
        reg.setParticipant(p);
        reg.setRegistrationDate("12-10-2025");
        registrationRepository.save(reg);
    }
}
