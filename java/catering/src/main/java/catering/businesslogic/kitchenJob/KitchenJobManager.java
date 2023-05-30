package catering.businesslogic.kitchenJob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KitchenJobManager {

    public KitchenJobManager() {
        KitchenJob.loadAllKitchenJobs();
    }

    public ObservableList<KitchenJob> getKitchenJobs() {
        return FXCollections.unmodifiableObservableList(KitchenJob.getAllKitchenJobs());
    }
}

