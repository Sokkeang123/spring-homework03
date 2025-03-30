package org.example._14_sieb_sokkeang_pp_web_homework003.service.imp;

import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.AttendeeRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.repository.AttendeeRepository;
import org.example._14_sieb_sokkeang_pp_web_homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImp(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        return attendeeRepository.getAllAttendees(size, page);
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.createAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer id, AttendeeRequest attendeeRequest) {
        Attendee existingAttendee = attendeeRepository.getAttendeeById(id);
        if (existingAttendee == null) {
            return null;
        }
        return attendeeRepository.updateAttendee(id, attendeeRequest);
    }

//    @Override
//    public void deleteAttendee(Integer id) {
//        attendeeRepository.deleteAttendee(id);
//    }

    @Override
    public void deleteAttendee(Integer id) {
        attendeeRepository.deleteEventAttendeeRecords(id);
        attendeeRepository.deleteAttendee(id);
    }
}