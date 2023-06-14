package catering.businesslogic.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing_total {
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

        try {
            System.out.println("Creating summary sheet #1...");
			SummarySheet ss = app.getSummarySheetManager().createSummarySheet(ev, serv);
			System.out.println("Created summary sheet #1");

            try {
                System.out.println("Trying to reopen summary sheet #1...");
                app.getSummarySheetManager().chooseSummarySheet(ev,serv);
                System.out.println("Summary sheet #1 can be reopened correctly!");
            } catch (Exception e) {
                System.out.println("Error in reopening summary sheet #1");
                e.printStackTrace();
            }
            	List<KitchenJob> kjs = KitchenJob.loadAllKitchenJobs();
			// find first kitchen job not in summary sheet usinge the equals method
			boolean found;
			KitchenJob kj = null;
			for (KitchenJob kitchenJob : kjs) {
				found = true;
				for (Task ts : app.getSummarySheetManager().getCurrentSummarySheet().getTasks()) {
					if (kitchenJob.equals(ts.getKitchenJob())) {
						found = false;
						break;
					}
				}
				if (found){
					kj = kitchenJob;
					break;
				}
			}
			if (kj == null) {
				System.out.println("No kitchen job not in the service found");
				return;
			}
			System.out.println(String.format("Trying to add kitchen job %s(id: %d) to the summary sheet", kj.getName(), kj.getId()));
			try {
				app.getSummarySheetManager().addKitchenJob(kj);
				System.out.println("Kitchen job added correctly!");
				try {
					System.out.println("Checking if the add is persistent...");
					boolean found2 = false;
					for (Task ts : app.getSummarySheetManager().getCurrentSummarySheet().getTasks()) {
						if (ts.getKitchenJob().equals(kj)) {
							System.out.println("Kitchen job added correctly!");
							found2 = true;
							break;
						}
					}
					if (!found2)
						System.out.println("Kitchen job not added correctly! ERROR");
					try {
						// try to remove the kitchen job
						System.out.println(String.format("Trying to remove kitchen job %s(id: %d) from the summary sheet", kj.getName(), kj.getId()));
						app.getSummarySheetManager().removeKitchenJob(kj);
						// chef if it is not in tasklist
						boolean found3 = false;
						for (Task ts : app.getSummarySheetManager().getCurrentSummarySheet().getTasks()) {
							if (ts.getKitchenJob().equals(kj)) {
								System.out.println("Kitchen job not removed correctly! ERROR");
								found3 = true;
								break;
							}
						}
						if (!found3)
							System.out.println("Kitchen job removed correctly!");
					} catch (Exception e) {
						System.out.println("Kitchen job not removed correctly! ERROR");
						e.printStackTrace();
					}

                    try {
                        System.out.println("Trying to get the shift board...");
                        app.getShiftManager().getCurrentBoard();
                        System.out.println("Shift board correctly loaded");
                    } catch (Exception e) {
                        System.out.println("Error in getting the shift board");
                        e.printStackTrace();
                    }
					
				} catch (Exception e) {
					System.out.println("Error while adding kitchen job to Sumamry Sheet");
					e.printStackTrace();
				}

                System.out.println("Getting all the tasks of the sheet...");
            List<Task> tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            Task t = tl.get(0);
            System.out.println("Trying to assign first shift to task...");
            app.getSummarySheetManager().assignTask(
                t,
                // Optional.of(Shift.loadShift(1)), // Shift
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

            app.getSummarySheetManager().removeTaskAssignment(t);
            if (
                t.getShift() == null &&
                t.getCook() == null &&
                t.getEstimatedTime() == null &&
                t.getQuantity() == null
            ){
                System.out.println("Assignment removed");
            }else{
                System.out.println("Assignment not removed");
                return;
            }
            System.out.println("Checking if the assignment is persistent...");
            app.getSummarySheetManager().chooseSummarySheet(ev, serv);
            tl = app.getSummarySheetManager().getCurrentSummarySheet().getTasks();
            t = tl.get(0);
            
            System.out.println("Assignment removed");
			} catch (UseCaseLogicException e) {
				System.out.println("Kitchen job not added correctly!");
				e.printStackTrace();
			}
            System.out.println("All test passed!");
            
        } catch (Exception e) {
            System.out.println("Error somewhere in the test");
            e.printStackTrace();
        }
    }
}

