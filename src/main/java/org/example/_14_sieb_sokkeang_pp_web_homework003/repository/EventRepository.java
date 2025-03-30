package org.example._14_sieb_sokkeang_pp_web_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Event;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventRequest;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.EventUpdateRequest;

import java.util.List;

@Mapper
public interface EventRepository {

    // Get all events with pagination
    @Select("SELECT * FROM events OFFSET (#{size} * (#{page} - 1)) LIMIT #{size}")
    @Results(id="eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.example._14_sieb_sokkeang_pp_web_homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "getAttendeesByEventId"))
    })
    List<Event> findAllEvents(@Param("page") Integer page, @Param("size") Integer size);

    // Get event by ID
    @Select("SELECT * FROM events WHERE event_id = #{id}")
    @ResultMap("eventMapper")
//    @Results({
//            @Result(property = "eventId", column = "event_id"),
//            @Result(property = "eventName", column = "event_name"),
//            @Result(property = "eventDate", column = "event_date"),
//            @Result(property = "venue", column = "venue_id",
//                    one = @One(select = "getVenueById")),
//            @Result(property = "attendees", column = "event_id",
//                    many = @Many(select = "org.example._14_sieb_sokkeang_pp_web_homework003.repository.EventRepository.getAttendeesByEventId"))
//    })
    Event findEventById(@Param("id") Integer id);

    // Create event
    @Select("INSERT INTO events (event_name, event_date, venue_id) VALUES (#{event.eventName}, #{event.eventDate}, #{event.venueId}) RETURNING *")
//    @Options(useGeneratedKeys = true, keyProperty = "eventId", keyColumn = "event_id")
    Integer createEvent(@Param("event") EventRequest eventRequest);

    // Add attendee to event
    @Insert("INSERT INTO event_attendee (event_id, attendee_id) VALUES (#{eventId}, #{attendeeId})")
    void addAttendeeToEvent(@Param("eventId") Integer eventId,
                            @Param("attendeeId") Integer attendeeId);

    //1. Update event
    @Select("UPDATE events SET event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId} WHERE event_id = #{id} RETURNING event_id")
    Event updateEvent(@Param("id") Integer id,@Param("event") EventUpdateRequest eventUpdateRequest);

    //2. update event_attendee
    @Insert("""
    INSERT INTO event_attendee(event_id, attendee_id) VALUES (#{eventId},#{attendeeId})
""")
    void UpdateEventAttendee(Integer eventId,Integer attendeeId);

    //3. delete event_attendee
    @Select("DELETE FROM event_attendee WHERE event_id=#{eventId}")
    void deleteEventAttendee(Integer eventId);


    // Delete event
    @Delete("DELETE FROM events WHERE event_id = #{id}")
    void deleteEvent(@Param("id") Integer id);

    // Delete event_attendee relationships
    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteEventAttendees(@Param("eventId") Integer eventId);

    // Helper method for venue mapping
    @Select("SELECT * FROM venues WHERE venue_id = #{id}")
    Venue getVenueById(@Param("id") Integer id);

    // Helper method for attendees mapping
    @Select("SELECT a.* FROM attendees a JOIN event_attendee ea ON a.attendee_id = ea.attendee_id WHERE ea.event_id = #{eventId}")
    @Result(property = "attendeeId" , column = "attendee_id")
    @Result(property = "attendeeName", column = "attendee_name")
    List<Attendee> getAttendeesByEventId(@Param("eventId") Integer eventId);

//    Event createEvent(EventRequest eventRequest);

//    Event updateEvent(Integer id, EventRequest eventRequest);

//    Event updateEvent(Integer id, EventRequest eventRequest);

//    Event updateEvent(Integer id, EventRequest eventRequest);

    // update course by id
//    @Select("""
//    UPDATE events
//
//
//    SET event_name = #{course.courseName}, location = #{event.location}, venue_id = #{event.venueId}
//    WHERE event_id = #{id}
//    RETURNING *
//""")
//    @ResultMap("eventMapper")
//    Event updateEvent(@Param("id") Integer id, @Param("course") EventRequest courseUpdateRequest);


}