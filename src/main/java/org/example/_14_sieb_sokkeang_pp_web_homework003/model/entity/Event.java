package org.example._14_sieb_sokkeang_pp_web_homework003.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer eventId;
    private String eventName;
    private Date eventDate;
    private Venue venue; // Changed from List<Venue> to Venue (one-to-many relationship)
    private List<Attendee> attendees; // Added to reflect the many-to-many relationship
}