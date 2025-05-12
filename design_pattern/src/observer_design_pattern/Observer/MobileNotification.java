package observer_design_pattern.Observer;

import
observer_design_pattern.Observable.StockObservable;

public class MobileNotification implements NotificationObserver{

    StockObservable stockObservable;
    String email;

    public MobileNotification(String email, StockObservable stockObservable) {
        this.stockObservable = stockObservable;
        this.email = email;
    }

    @Override
    public void update() {
        System.out.println("MobileNotification" + stockObservable.getData());
        sendMail();
    }

    public void sendMail() {
        System.out.println("Sending email to..." + this.email);
    }
}
