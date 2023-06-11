package catering.businesslogic.tests;

import java.util.List;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.summarySheet.Task;
import catering.businesslogic.user.User;
import catering.persistence.PersistenceManager;

public class TestCatERing2_2a {
	public static void main(String[] args) {
		if(!PersistenceManager.testDB()) {
			System.out.println("DB connection failed");
			return;
		}

		CatERing app = CatERing.getInstance();
		app.getUserManager().loginWithUsername("Lidia");// Chef have the h role in the db
		User user = app.getUserManager().getCurrentUser();
		System.out.println("Logged in as " + user.getUserName());

		// Recovering the summary sheet 
		EventInfo ev = app.getEventManager().getEventsInfo().get(0);
		ServiceInfo serv = ev.getServices().get(0);
		System.out.println("Testing for event: " + ev.getName() + " and service: " + serv.getName());
		
		try {
			app.getSummarySheetManager().createSummarySheet(ev, serv);
			System.out.println("Created or recovered Summary Sheet correctly!");
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
					
				} catch (Exception e) {
					System.out.println("Error while adding kitchen job to Sumamry Sheet");
					e.printStackTrace();
				}
			} catch (UseCaseLogicException e) {
				System.out.println("Kitchen job not added correctly!");
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Error in recovering summary sheet");
			e.printStackTrace();
		}
	}
}
