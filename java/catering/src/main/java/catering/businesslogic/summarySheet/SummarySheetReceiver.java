package catering.businesslogic.summarySheet;

public interface SummarySheetReceiver {

    void updateKitchenJobAdded(SummarySheet currentSheet, KitchenJob ag);
    void updateKitchenJobRemoved(SummarySheet currentSheet, KitchenJob ag);

}
