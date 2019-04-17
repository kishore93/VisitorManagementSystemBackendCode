package com.infy.visitor.management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.infy.visitor.management.entity.CardType;
import com.infy.visitor.management.entity.Location;
import com.infy.visitor.management.entity.VisitorType;
import com.infy.visitor.management.repository.CardTypeRepository;
import com.infy.visitor.management.repository.EmployeeRepository;
import com.infy.visitor.management.repository.LocationRepository;
import com.infy.visitor.management.repository.VisitorTypeRepository;

@SpringBootApplication
public class VisitorManagementApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(VisitorManagementApplication.class, args);
		String[] activeProfile = applicationContext.getEnvironment().getActiveProfiles();
		if (activeProfile[1].equalsIgnoreCase("dev")) {

			cardType(applicationContext.getBean(CardTypeRepository.class));
			// Authoties(applicationContext.getBean(AuthorityRepository.class));
			Employies(applicationContext.getBean(EmployeeRepository.class));
			visitorType(applicationContext.getBean(VisitorTypeRepository.class));
			locations(applicationContext.getBean(LocationRepository.class));
		}

	}

	static void cardType(CardTypeRepository cardTypeRepository) {
		cardTypeRepository.save(new CardType("Adhaar Card"));
		cardTypeRepository.save(new CardType("PAN Card"));
	}
	
	static void locations(LocationRepository locationRepository ) {
		locationRepository.save(new Location("HYD-SEZ"));
		locationRepository.save(new Location("HYD-STP"));
		locationRepository.save(new Location("BLR-STP"));
		locationRepository.save(new Location("PUNE-STP"));
	}

	static void visitorType(VisitorTypeRepository visitorTypeRepository) {
		List<VisitorType> visitorTypes = new ArrayList<>();

		visitorTypes.add(new VisitorType("New Joinees"));
		visitorTypes.add(new VisitorType("Employee who has forgotten/lost his card"));
		visitorTypes.add(new VisitorType("Visitor"));
		visitorTypes.add(new VisitorType("VIP"));
		visitorTypes.add(new VisitorType("Family"));
		visitorTypes.add(new VisitorType("Conference Attendee"));
		visitorTypes.add(new VisitorType("Client"));
		visitorTypes.add(new VisitorType("Vendor"));
		visitorTypes.add(new VisitorType("Interviewee"));
		visitorTypes.add(new VisitorType("Guest"));
		visitorTypeRepository.saveAll(visitorTypes);

	}

	static void Employies(EmployeeRepository employeeRepository) {
		com.infy.visitor.management.entity.Employee employee1 = new com.infy.visitor.management.entity.Employee();
		employee1.setFullName("Neeraj S");
		employee1.setEmployeeCode((long) 1019657);
		employee1.setPassword("test");
		employee1.setRole("EMP");
		employeeRepository.save(employee1);

		com.infy.visitor.management.entity.Employee employee = new com.infy.visitor.management.entity.Employee();
		employee1.setFullName("shyam Rajat");
		employee1.setEmployeeCode((long) 1019652);
		employee.setPassword("test");
		employee.setRole("SECURITY");
		employeeRepository.save(employee);

	}

}
