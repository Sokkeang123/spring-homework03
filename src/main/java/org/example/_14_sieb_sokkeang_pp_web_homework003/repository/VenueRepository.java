package org.example._14_sieb_sokkeang_pp_web_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
import org.example._14_sieb_sokkeang_pp_web_homework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {

    // Get all venues with pagination
    @Select("""
        SELECT * FROM venues
        OFFSET #{size} * (#{page} - 1)
        LIMIT #{size}
    """)
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    List<Venue> getAllVenues(@Param("size") Integer size, @Param("page") Integer page);

    // Get venue by ID
    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    @ResultMap("venueMapper")
    Venue getVenueById(@Param("venueId") Integer venueId);

    // Add new venue
    @Select("""
        INSERT INTO venues (venue_name, location)
        VALUES (#{VenueRequest.venueName}, #{VenueRequest.location})
        RETURNING *;
    """)
    @ResultMap("venueMapper")
    Venue createVenue(@Param("VenueRequest") VenueRequest venueRequest);

    // Update venue by ID
    @Select("""
        UPDATE venues
        SET venue_name = #{VenueRequest.venueName}, location = #{VenueRequest.location}
        WHERE venue_id = #{venueId}
        RETURNING *;
    """)
    @ResultMap("venueMapper")
    Venue updateVenue(@Param("venueId") Integer venueId, @Param("VenueRequest") VenueRequest venueRequest);

    // Delete venue by ID
    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    void deleteVenue(@Param("venueId") Integer venueId);
}