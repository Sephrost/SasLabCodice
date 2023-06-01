package catering.businesslogic.summarySheet;

import catering.businesslogic.kitchenJob.KitchenJob;

public interface SummarySheetReceiver {


    void notifySummarySheetCreated(SummarySheet sheet);
    void notifySummarySheetSelected(SummarySheet sheet);
    void notifyKitchenJobAdded(KitchenJob kJ);

}
