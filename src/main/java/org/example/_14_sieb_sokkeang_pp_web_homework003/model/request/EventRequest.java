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

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50, message = "Event Name must be 1 and 50 character")
    private String eventName;
    private Date eventDate;
    private Integer venueId;
    private List<Integer> attendeeIds;
}