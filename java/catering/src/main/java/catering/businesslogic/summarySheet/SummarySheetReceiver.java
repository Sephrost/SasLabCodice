package catering.businesslogic.summarySheet;

import catering.businesslogic.kitchenJob.KitchenJob;

public interface SummarySheetReceiver {


    void notifySummarySheetCreated(SummarySheet sheet);
    void updateKitchenJobAdded(SummarySheet currentSheet, KitchenJob kJ);
    void updateKitchenJobRemoved(SummarySheet currentSheet, KitchenJob kJ);

}
