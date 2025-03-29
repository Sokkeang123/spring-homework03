package org.example._14_sieb_sokkeang_pp_web_homework003.service.imp;

import lombok.RequiredArgsConstructor;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.AttendeeRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.AttendeeRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.AttendeeService;
import org.example._14_sieb_sokkeang_pp_web_homework003.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImp implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        if (page < 1 || size < 1) {
            throw new IllegalArgumentException("Page and size must be greater than 0");
        }
        return attendeeRepository.getAllAttendees(size, page);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if (attendee == null) {
            throw new ResourceNotFoundException("Attendee with ID " + attendeeId + " not found");
        }
        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        if (attendeeRequest.getAttendeeName() == null || attendeeRequest.getEmail() == null) {
            throw new IllegalArgumentException("Attendee name and email cannot be null");
        }
        return attendeeRepository.createAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        // Check if attendee exists
        Attendee existingAttendee = attendeeRepository.getAttendeeById(attendeeId);
        if (existingAttendee == null) {
            throw new ResourceNotFoundException("Attendee with ID " + attendeeId + " not found");
        }
        if (attendeeRequest.getAttendeeName() == null || attendeeRequest.getEmail() == null) {
            throw new IllegalArgumentException("Attendee name and email cannot be null");
        }
        return attendeeRepository.updateAttendee(attendeeId, attendeeRequest);
    }

    @Override
    public void deleteAttendee(Integer attendeeId) {
        // Check if attendee exists
        Attendee existingAttendee = attendeeRepository.getAttendeeById(attendeeId);
        if (existingAttendee == null) {
            throw new ResourceNotFoundException("Attendee with ID " + attendeeId + " not found");
        }
        attendeeRepository.deleteAttendee(attendeeId);
    }
}