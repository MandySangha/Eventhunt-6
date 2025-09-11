package ca.sheridancollege.acevedpa.bootstrap;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.acevedpa.domain.Address;
import ca.sheridancollege.acevedpa.domain.Categories;
import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.domain.Feedback;
import ca.sheridancollege.acevedpa.domain.Organizer;
import ca.sheridancollege.acevedpa.domain.Roles;
import ca.sheridancollege.acevedpa.domain.Ticket;
import ca.sheridancollege.acevedpa.domain.User;
import ca.sheridancollege.acevedpa.repositories.AddressRepository;
import ca.sheridancollege.acevedpa.repositories.EventRepository;
import ca.sheridancollege.acevedpa.repositories.FeedbackRepository;
import ca.sheridancollege.acevedpa.repositories.OrganizerRepository;
import ca.sheridancollege.acevedpa.repositories.TicketRepository;
import ca.sheridancollege.acevedpa.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DemoBootstrap implements CommandLineRunner {

	private AddressRepository addressRepository;
	private OrganizerRepository organizerRepository;
	private EventRepository eventRepository;
	private UserRepository userRepository;
	private TicketRepository ticketRepository;
	private FeedbackRepository feedbackRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

		// Organizers
		Organizer organizer1 = organizerRepository.save(Organizer.builder()
				.orgName("Monsters Inc.").orgEmail("monster@gmail.com").build());

		Organizer organizer2 = organizerRepository.save(Organizer.builder()
				.orgName("SuperHeroes Inc.").orgEmail("heroes@gmail.com").build());

		// User
		Date userRegDate = sdf.parse("2025-03-01");
		User user1 = userRepository.save(User.builder()
				.username("user123")
				.userEmail("user123@gmail.com")
				.userPassword("1234")
				.role(Roles.EVENTHUNT_USER)
				.registrationDate(userRegDate)
				.build());

		// Time slots
		Time time1 = new Time(timeFormat.parse("18:30").getTime());
		Time time2 = new Time(timeFormat.parse("14:00").getTime());
		Time time3 = new Time(timeFormat.parse("10:00").getTime());
		Time time4 = new Time(timeFormat.parse("16:45").getTime());

		// EVENT 1
		Event event1 = eventRepository.save(Event.builder()
				.eventName("Meet A Monster")
				.description("Get to know a fellow monster!")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Apple Line 1").addressLine2("Apt. 5").city("Toronto").province("ON").country("Canada").postalCode("123JND").build()))
				.date(sdf.parse("2025-03-30"))
				.time(time1)
				.organizer(organizer1)
				.category(Categories.NETWORKING)
				.maxAttendees(30)
				.ticketPrice(new BigDecimal("20.00"))
				.build());

		// EVENT 2
		Event event2 = eventRepository.save(Event.builder()
				.eventName("Superhero Meetup")
				.description("Join us for a superhero gathering!")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Orange Line 2").addressLine2("Suite 101").city("Ottawa").province("ON").country("Canada").postalCode("234KLM").build()))
				.date(sdf.parse("2025-04-10"))
				.time(time2)
				.organizer(organizer2)
				.category(Categories.NETWORKING)
				.maxAttendees(50)
				.ticketPrice(new BigDecimal("25.00"))
				.build());

		// EVENT 3
		Event event3 = eventRepository.save(Event.builder()
				.eventName("Food Fest")
				.description("Taste dishes from around the world")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Peach Blvd").addressLine2("Unit 12").city("Mississauga").province("ON").country("Canada").postalCode("M1M1M1").build()))
				.date(sdf.parse("2025-04-20"))
				.time(time3)
				.organizer(organizer2)
				.category(Categories.FOOD_AND_DRINK)
				.maxAttendees(100)
				.ticketPrice(new BigDecimal("15.00"))
				.build());

		// EVENT 4
		Event event4 = eventRepository.save(Event.builder()
				.eventName("Tech Talk")
				.description("Explore the latest in tech")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Tech Street").addressLine2("Hall A").city("Brampton").province("ON").country("Canada").postalCode("T2T2T2").build()))
				.date(sdf.parse("2025-04-25"))
				.time(time4)
				.organizer(organizer1)
				.category(Categories.TECHNOLOGY)
				.maxAttendees(80)
				.ticketPrice(new BigDecimal("30.00"))
				.build());

		// EVENT 5
		Event event5 = eventRepository.save(Event.builder()
				.eventName("Art Show")
				.description("Local artists showcase their work")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Gallery Ave").addressLine2("Room 10").city("Hamilton").province("ON").country("Canada").postalCode("A1A1A1").build()))
				.date(sdf.parse("2025-05-01"))
				.time(time2)
				.organizer(organizer1)
				.category(Categories.ARTS)
				.maxAttendees(40)
				.ticketPrice(new BigDecimal("12.00"))
				.build());

		// EVENT 6
		Event event6 = eventRepository.save(Event.builder()
				.eventName("Kids Carnival")
				.description("Fun-filled day for families")
				.eventAddress(addressRepository.save(Address.builder().addressLine1("Fun Park").addressLine2("Zone C").city("Toronto").province("ON").country("Canada").postalCode("Z9Z9Z9").build()))
				.date(sdf.parse("2025-05-10"))
				.time(time3)
				.organizer(organizer2)
				.category(Categories.KIDS_AND_FAMILY)
				.maxAttendees(60)
				.ticketPrice(new BigDecimal("10.00"))
				.build());

		// TICKETS
		ticketRepository.save(Ticket.builder().ticketPurchaseDate(sdf.parse("2025-03-30")).user(user1).event(event1).build());
		ticketRepository.save(Ticket.builder().ticketPurchaseDate(sdf.parse("2025-03-30")).user(user1).event(event1).build());
		ticketRepository.save(Ticket.builder().ticketPurchaseDate(sdf.parse("2025-04-10")).user(user1).event(event2).build());

		// FEEDBACK
		feedbackRepository.save(Feedback.builder()
				.rating(5L)
				.comment("Great event!")
				.feedbackDate(new Date())
				.event(event1)
				.user(user1)
				.build());
	}
}
