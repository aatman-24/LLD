package observer_design_pattern;

import
observer_design_pattern.Observable.IPhoneStockObservable;
import
observer_design_pattern.Observer.MobileNotification;
import
observer_design_pattern.Observer.WebNotification;

public class ObserverDesignClient {

    public static void run() {

        IPhoneStockObservable iPhoneStockObservable = new IPhoneStockObservable();

        MobileNotification mobileNotification = new MobileNotification("abc@gmail.com", iPhoneStockObservable);
        MobileNotification mobileNotification1 = new MobileNotification("cde@gmail.com", iPhoneStockObservable);
        WebNotification webNotification = new WebNotification("web@gmail.com", iPhoneStockObservable);

        iPhoneStockObservable.add(mobileNotification);
        iPhoneStockObservable.add(mobileNotification1);
        iPhoneStockObservable.add(webNotification);

        iPhoneStockObservable.setData(3);

    }
}
