package observer_design_pattern.Observable;

import
observer_design_pattern.Observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class IPhoneStockObservable implements StockObservable {

    List<NotificationObserver> observerList = new ArrayList<>();
    int currentStock = 0;

    @Override
    public void add(NotificationObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public int getData() {
        return currentStock;
    }

    public void notifyObserver() {
        for(NotificationObserver observer: observerList) {
            observer.update();
        }
    }

    @Override
    public void setData(int newStock) {
        if(currentStock == 0) {
            notifyObserver();
        }
        currentStock += newStock;
    }

}
