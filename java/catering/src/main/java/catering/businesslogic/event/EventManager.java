package catering.businesslogic.event;

import javafx.collections.ObservableList;

public class EventManager {
    public ObservableList<EventInfo> getEventsInfo() {
        return EventInfo.loadAllEventInfo();
    }
}
