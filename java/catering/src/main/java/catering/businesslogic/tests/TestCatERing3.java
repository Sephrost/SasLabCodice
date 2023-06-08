package catering.businesslogic.tests;

import java.util.List;

import catering.businesslogic.CatERing;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing3 {

	public static void main(String[] args) {
		if(!PersistenceManager.testDB()) {
			System.out.println("DB connection failed");
			return;
		}
	
		CatERing app = CatERing.getInstance();
		app.getUserManager().loginWithUsername("Lidia");// Chef have the h role in the db
		User user = app.getUserManager().getCurrentUser();
		System.out.println("Logged in as " + user.getUserName());

		// Get needed parameters
		EventInfo ev = app.getEventManager().getEventsInfo().get(0);
		ServiceInfo serv = ev.getServices().get(0);
		System.out.println("Testing for event: " + ev.getName() + " and service: " + serv.getName());

		try {
			System.out.println("Creating or loading summary sheet...");
			app.getSummarySheetManager().createSummarySheet(ev,serv);
			System.out.println("Created or loaded summary sheet");

			System.out.println("Trying to swap first and last task...");
			List<Task> tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();

			Task t1 = tl.get(0);
			Task t2 = tl.get(tl.size()-1);

			app.getSummarySheetManager().reorderTask(t1, tl.size()-1);

			if (tl.get(0).equals(t2) && tl.get(tl.size()-1).equals(t1)) {
				System.out.println("Tasks swapped correctly!");
			} else {
				System.out.println("Tasks not swapped correctly! ERROR");
			}

			
		} catch (Exception e) {
			System.out.println("Error in swapping tasks");
			e.printStackTrace();
		}
	}
	
}
