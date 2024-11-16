package observer_design_pattern.Observer;

import
observer_design_pattern.Observable.StockObservable;

public class WebNotification implements  NotificationObserver{

    StockObservable stockObservable;
    String email;

    public WebNotification(String email, StockObservable stockObservable) {
        this.stockObservable = stockObservable;
        this.email = email;
    }

    @Override
    public void update() {
        System.out.println("WebNotification" + stockObservable.getData());
        sendMail();
    }

    @Override
    public void sendMail() {
        System.out.println("Sending email to..." + this.email);
    }
}
