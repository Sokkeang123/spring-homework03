-- Create Venue Table
CREATE TABLE venues (
       venue_id SERIAL PRIMARY KEY,
       venue_name VARCHAR(40) NOT NULL,
       location VARCHAR(50) NOT NULL
);

-- Create Attendees Table
CREATE TABLE attendees (
    attendee_id serial primary key ,
    attendee_name varchar(200) NOT NULL ,
    email varchar(200) NOT NULL
);

-- Create events table
CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(100) NOT NULL,
                        event_date DATE NOT NULL,
                        venue_id INTEGER NOT NULL,
                        FOREIGN KEY (venue_id) REFERENCES venues(venue_id)
);

-- Create Event_Attendee Table
CREATE TABLE event_attendee (
                                id SERIAL PRIMARY KEY,
                                event_id INTEGER NOT NULL,
                                attendee_id INTEGER NOT NULL,
                                FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
                                FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE,
                                UNIQUE (event_id, attendee_id)
);