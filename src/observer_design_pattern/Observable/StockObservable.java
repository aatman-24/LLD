package observer_design_pattern.Observable;

import
observer_design_pattern.Observer.NotificationObserver;

public interface StockObservable {
    void add(NotificationObserver observer);
    void remove(NotificationObserver observer);
    int getData();
    void setData(int newStock);
}
