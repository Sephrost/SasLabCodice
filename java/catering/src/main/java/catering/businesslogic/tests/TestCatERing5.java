package catering.businesslogic.tests;

import java.util.List;
import java.util.Optional;

import catering.businesslogic.CatERing;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing5 {
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

        try{
            System.out.println("Creating or loading summary sheet...");
            app.getSummarySheetManager().createSummarySheet(ev,serv);
            System.out.println("Created or loaded summary sheet");
            
            // get all the Tasks
            System.out.println("Getting all the tasks of the sheet...");
            List<Task> tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            Task t = tl.get(0);
            System.out.println("Trying to assign first shift to task...");
            app.getSummarySheetManager().assignTask(t, Optional.of(app.getShiftManager().getShifts().get(0)), Optional.empty(), Optional.empty(), Optional.empty());
            if (t.getShift()!=null) {
                System.out.println("Shift assigned correctly!");
            } else {
                System.out.println("Shift not assigned correctly! ERROR");
            }

        }catch(Exception e){
            System.out.println("Error somewhere");
			e.printStackTrace();
        }
    }
}
