package catering.persistence;

import java.util.List;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.SummarySheetException;
import catering.businesslogic.summarySheet.SummarySheetReceiver;
import catering.businesslogic.summarySheet.Task;

public class SummarySheetPersistence implements SummarySheetReceiver{

	@Override
	public void notifySummarySheetCreated(SummarySheet s) {
		SummarySheet.saveSheet(s);	
	}

	@Override
	public void notifySummarySheetSelected(SummarySheet s) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyKitchenJobAdded(SummarySheet s, Task t) {
		Task.saveTask(t);
		SummarySheet.insertTaskIntoSummarySheet(s, t);
	}

	@Override
	public void notifyKitchenJobRemoved(SummarySheet s, List<Task> tl) {
		for (Task t : tl) {
			Task.removeTask(t.getKitchenJob());
			SummarySheet.removeEntry(s, t);
		}
		
	}

	@Override
	public void notifyTaskOrderModified() {
		// TODO Auto-generated method stub
	}


	
}