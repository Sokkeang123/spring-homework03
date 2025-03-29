package org.example._14_sieb_sokkeang_pp_web_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    // Get all attendees with pagination
    @Select("""
        SELECT * FROM attendees
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendee> getAllAttendees(@Param("size") Integer size, @Param("page") Integer page);

    // Get attendee by ID
    @Select("SELECT * FROM attendees WHERE attendee_id = #{attendeeId}")
    @ResultMap("attendeeMapper")
    Attendee getAttendeeById(@Param("attendeeId") Integer attendeeId);

    // Add new attendee
    @Select("""
        INSERT INTO attendees (attendee_name, email)
        VALUES (#{attendeeRequest.attendeeName}, #{attendeeRequest.email})
        RETURNING *;
    """)
    @ResultMap("attendeeMapper")
    Attendee createAttendee(@Param("attendeeRequest") AttendeeRequest attendeeRequest);

    // Update attendee by ID
    @Select("""
        UPDATE attendees
        SET attendee_name = #{attendeeRequest.attendeeName}, email = #{attendeeRequest.email}
        WHERE attendee_id = #{attendeeId}
        RETURNING *;
    """)
    @ResultMap("attendeeMapper")
    Attendee updateAttendee(@Param("attendeeId") Integer attendeeId, @Param("attendeeRequest") AttendeeRequest attendeeRequest);

    // Delete attendee by ID
    @Delete("DELETE FROM attendees WHERE attendee_id = #{attendeeId}")
    void deleteAttendee(@Param("attendeeId") Integer attendeeId);
}