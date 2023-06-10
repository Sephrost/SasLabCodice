package catering.businesslogic.tests;

import java.util.List;

import catering.businesslogic.CatERing;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing5b {
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
            app.getSummarySheetManager().createSummarySheet(ev,serv);
            System.out.println("Created or loaded summary sheet");
            
            // get all the Tasks
            System.out.println("Getting all the tasks of the sheet...");
            List<Task> tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            Task t = tl.get(0);
            System.out.println("Trying to set first task as completed...");
            app.getSummarySheetManager().taskCompleted(t);
            if (t.isCompleted()) {
                System.out.println("Task completed");
            } else {
                System.out.println("Task not completed");
                return;
            }
            System.out.println("Checking if set is persistent...");
            app.getSummarySheetManager().chooseSummarySheet(ev, serv);
            tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            t = tl.get(0);
            if (t.isCompleted()) {
                System.out.println("Completed task is persistent");
            } else {
                System.out.println("Completed task is not persistent");
                return;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
