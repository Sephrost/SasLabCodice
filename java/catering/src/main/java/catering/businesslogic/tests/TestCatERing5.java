package catering.businesslogic.tests;

import java.util.List;
import java.util.Optional;
import java.time.Duration;

import catering.businesslogic.CatERing;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.SummarySheetManager;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.businesslogic.user.UserManager;
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
            app.getSummarySheetManager().assignTask(
                t,
                // Optional.of(app.getShiftManager().getShifts().get(0)), // Shift
                Optional.ofNullable(null), // Shift
                Optional.of(User.loadUserById(4)), // Cook Marinella FTW
                Optional.of(Duration.ofHours(2)), // Duration
                Optional.of("Yes") // Quantity
            );
            System.out.println("Assigned Parameters to task");
            System.out.println("Checking if the assignment is persistent...");
            app.getSummarySheetManager().chooseSummarySheet(ev, serv);
            tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            t = tl.get(0);
            if (
                t.getShift() != null &&
                t.getCook() != null &&
                t.getEstimatedTime() != null &&
                t.getQuantity() != null
            ){
                System.out.println("Assignment is persistent");
            }else{
                System.out.println("Assignment is not persistent, something is wrong");
            }
        }catch(Exception e){
            System.out.println("Error somewhere");
			e.printStackTrace();
        }
    }
}
