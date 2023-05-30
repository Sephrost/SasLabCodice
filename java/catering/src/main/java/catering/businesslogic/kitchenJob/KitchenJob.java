package catering.businesslogic.kitchenJob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class KitchenJob {
    private static Map<Integer, KitchenJob> all = new HashMap<>();

    private int id;
    private String name;

    private KitchenJob() {

    }

    public KitchenJob(String name) {
        id = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<KitchenJob> loadAllKitchenJobs() {
        String query = "SELECT * FROM KitchenJobs";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (all.containsKey(id)) {
                    KitchenJob rec = all.get(id);
                    rec.name = rs.getString("name");
                } else {
                    KitchenJob rec = new KitchenJob(rs.getString("name"));
                    rec.id = id;
                    all.put(rec.id, rec);
                }
            }
        });
        ObservableList<KitchenJob> ret =  FXCollections.observableArrayList(all.values());
        Collections.sort(ret, new Comparator<KitchenJob>() {
            @Override
            public int compare(KitchenJob o1, KitchenJob o2) {
                return (o1.getName().compareTo(o2.getName()));
            }
        });
        return ret;
    }

    public static ObservableList<KitchenJob> getAllKitchenJobs() {
        return FXCollections.observableArrayList(all.values());
    }

    public static KitchenJob loadKitchenJobById(int id) {
        if (all.containsKey(id)) return all.get(id);
        KitchenJob rec = new KitchenJob();
        String query = "SELECT * FROM KitchenJobs WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                    rec.name = rs.getString("name");
                    rec.id = id;
                    all.put(rec.id, rec);
            }
        });
        return rec;
    }


}
