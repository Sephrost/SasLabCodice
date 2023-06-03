package catering.persistence;

import catering.businesslogic.summarySheet.SummarySheet;
import catering.businesslogic.summarySheet.SummarySheetReceiver;
import catering.businesslogic.summarySheet.Task;

public class SummarySheetPersistence implements SummarySheetReceiver{

	@Override
	public void notifySummarySheetCreated(SummarySheet s) {
		SummarySheet.saveSheet(s);	
	}
	
}