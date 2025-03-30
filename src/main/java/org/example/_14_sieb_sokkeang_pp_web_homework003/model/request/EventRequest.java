//package org.example._14_sieb_sokkeang_pp_web_homework003.model.request;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Attendee;
//import org.example._14_sieb_sokkeang_pp_web_homework003.model.entity.Venue;
//
//import java.util.Date;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class EventRequest {
//    private String eventName;
//    private Date eventDate;
//    private Venue venue; // Changed from List<Venue> to Venue
//    private List<Attendee> attendees; // Added to allow assigning attendees
//}

package org.example._14_sieb_sokkeang_pp_web_homework003.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private Date eventDate;
    private Integer venueId;  // Changed from Venue to Integer (just ID)
    private List<Integer> attendeeIds;  // Changed from List<Attendee> to List<Integer> (just IDs)
}