package org.example._14_sieb_sokkeang_pp_web_homework003.service;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Event;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventUpdateRequest;

import java.util.List;
public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size); // Changed return type to List<Event>
    Event getEventById(Integer id);
    Event createEvent(EventRequest eventRequest); // Changed parameter to EventRequest
    void deleteEventById(Integer id);
    Event updateEvent(Integer id, EventUpdateRequest eventUpdateRequest); // Fixed parameter
}