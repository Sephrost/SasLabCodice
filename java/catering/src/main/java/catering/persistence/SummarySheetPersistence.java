package catering.persistence;

import catering.businesslogic.kitchenJob.KitchenJob;
import catering.businesslogic.summarySheet.SummarySheet;
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
	public void notifyKitchenJobAdded(KitchenJob kj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyKitchenJobRemoved(KitchenJob kj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyTaskOrderModified() {
		// TODO Auto-generated method stub
	}


	
}