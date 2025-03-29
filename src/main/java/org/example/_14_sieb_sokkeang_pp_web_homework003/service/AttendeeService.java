package org.example._14_sieb_sokkeang_pp_web_homework003.service;

import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(Integer page, Integer size);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee createAttendee(AttendeeRequest attendeeRequest);

    Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest);

    void deleteAttendee(Integer attendeeId);
}