package catering.businesslogic.tests;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.user.User;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.persistence.PersistenceManager;

public class TestCatERing1 {

	public static void main(String[] args) {

		if(!PersistenceManager.testDB()) {
			System.out.println("DB connection failed");
			return;
		}
		
		CatERing app = CatERing.getInstance();

		// Testing Login
		app.getUserManager().loginWithUsername("Lidia");// Chef have the h role in the db
		User user = app.getUserManager().getCurrentUser();
		System.out.println("Logged in as " + user.getUserName());

		// Get needed parameters
		EventInfo ev = app.getEventManager().getEventsInfo().get(0);
		ServiceInfo serv = ev.getServices().get(0);
		System.out.println("Testing for event: " + ev.getName() + " and service: " + serv.getName());

		// Testing Creation of summary sheet
		try {
			System.out.println("Creating summary sheet #1...");
			SummarySheet ss = app.getSummarySheetManager().createSummarySheet(ev, serv);
			System.out.println("Created summary sheet #1");
			try {
				app.getSummarySheetManager().chooseSummarySheet(ev,serv);
				System.out.println("Summary sheet can be chosen correctly!");
			} catch (Exception e) {
				System.out.println("Error loading created summary sheet");
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Error in creating summary sheet #1");
			e.printStackTrace();
		}
		
		
	}

}
