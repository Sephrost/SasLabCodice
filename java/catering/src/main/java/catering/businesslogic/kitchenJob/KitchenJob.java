package catering.businesslogic.kitchenJob;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import catering.persistence.PersistenceManager;
import catering.persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;


public class KitchenJob {

		public enum KitchenJobType {
			RECIPE, PREPARATION
		}

    private int id;
    private String name;
		private KitchenJobType type;

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

    public boolean isRecipe() {
        return type == KitchenJobType.RECIPE;
    }

    public boolean isPreparation() {
        return type == KitchenJobType.PREPARATION;
    }

    private KitchenJobType getType(String type) {
        if (type.equals("RECIPE")) {
            return KitchenJobType.RECIPE;
        } else if (type.equals("PREPARATION")) {
            return KitchenJobType.PREPARATION;
        } else {
            return null;
        }
    }


    public String toString() {
        return "KitchenJob " + id + ": " + name + ' ' + (isRecipe() ? "recipe" : "preparation");
    }


    public static ObservableList<KitchenJob> loadAllKitchenJobs() {
        String query = "SELECT * FROM KitchenJobs";
        ObservableList<KitchenJob> list = FXCollections.observableArrayList();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                KitchenJob rec = new KitchenJob();
                rec.name = rs.getString("name");
                rec.id = rs.getInt("id");
                rec.type = rec.getType(rs.getString("type"));
                list.add(rec);
            }
        });
        return list;
    }

    public static KitchenJob loadKitchenJobById(int id) {
        String query = "SELECT * FROM KitchenJobs WHERE id = " + id;
        KitchenJob rec = new KitchenJob();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                rec.name = rs.getString("name");
                rec.id = rs.getInt("id");
                rec.type = rec.getType(rs.getString("type"));
            }
        });
        return rec;
    }


}
