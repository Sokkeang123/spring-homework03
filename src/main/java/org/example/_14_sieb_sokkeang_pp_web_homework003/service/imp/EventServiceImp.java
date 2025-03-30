package org.example._14_sieb_sokkeang_pp_web_homework003.service.imp;
import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Event;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventUpdateRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.AttendeeRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.EventRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        return eventRepository.findAllEvents(page, size);
    }
    @Override
    public Event getEventById(Integer id) {
        return eventRepository.findEventById(id);
    }
    @Override
    public Event createEvent(EventRequest eventRequest) {
        Integer eventId = eventRepository.createEvent(eventRequest);

        for (Integer attendeeId : eventRequest.getAttendeeIds()){
            eventRepository.addAttendeeToEvent(eventId, attendeeId);
        }
        return eventRepository.findEventById(eventId);
    }

    @Override
    public void deleteEventById(Integer id) {
        eventRepository.deleteEvent(id);

    }
    @Override
    public Event updateEvent(Integer id, EventUpdateRequest eventUpdateRequest) {
        Event event = eventRepository.findEventById(id);

        for (Integer attendeeId : eventUpdateRequest.getAttendeeIds()){ // [ 1,2,5,6,]
            Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
            if (attendee == null){
                return null;
            }
        }
        eventRepository.deleteEventAttendees(event.getEventId());

        for (Integer attendeeId : eventUpdateRequest.getAttendeeIds()){
            eventRepository.UpdateEventAttendee(event.getEventId(), attendeeId);
        }

        // set new event
        event.setEventName(eventUpdateRequest.getEventName());
        event.setEventDate(eventUpdateRequest.getEventDate());

        eventRepository.updateEvent(event.getEventId(), eventUpdateRequest);
        return eventRepository.findEventById(event.getEventId());
    }
}