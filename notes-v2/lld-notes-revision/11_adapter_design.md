### Design Pattern: Adapter Design Pattern(Structural)

---

Strucutral design pattern, helps to `simplify the design of a system with usage of right relationships`.

> **Adapter** is a structural design pattern that allows objects with incompatible interfaces to collaborate.

----

<u>Problem:</u>

I'm creating a stock market monitoring application, and dowloading all the data from diff sources in XML format, then displaying it nice charts. Now there is one 3rd party analytics library, which I want to interact. But issue is that, that lib accepting the JSON data, and in my all codebase I am managing the XML data, only. That lib, I don't own so I cannot make change there, and I am downloading data from diff sources, they won't be able to provide data in JSON format. 

<u>Possible Apporach:</u>

Yes, you are right you have to convert the XML into the JSON and then use that lib. But how to do effectively such that, next time If other lib expecting diff kind of format, I can do with minimum changes. 

<u>Solution:</u>

This is a special object that converts the interface of one object so that another object can understand it.  Example: We can create a `adapter` which basically accept the data in XML Format => Convert into the JSON => make call to 3rd party lib => return response. 

An adapter wraps one of the objects to hide the complexity of conversion happening behind the scenes. The wrapped object isn’t even aware of the adapter. For example, you can wrap an object that operates in meters and kilometers with an adapter that converts all of the data to imperial units such as feet and miles.

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-13-17-30-21-image.png)

## Structure

![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-13-17-31-52-image.png)

1. The **Client** is a class that contains the existing business logic of the program.

2. The **Client Interface(Targer interface)** describes a protocol that other classes must follow to be able to collaborate with the client code.

3. The **Service** is some useful class (usually 3rd-party or legacy). The client can’t use this class directly because it has an incompatible interface.

4. The **Adapter** is a class that’s able to work with both the client and the service: it implements the client interface, while wrapping the service object. The adapter receives calls from the client via the client interface and translates them into calls to the wrapped service object in a format it can understand.

5. The client code doesn’t get coupled to the concrete adapter class as long as it works with the adapter via the client interface. Thanks to this, you can introduce new types of adapters into the program without breaking the existing client code. This can be useful when the interface of the service class gets changed or replaced: you can just create a new adapter class without changing the client code.

```java
public class Adapter implements ClientInterface {

    Adaptee adaptee;

    Adapter(Adaptee adaptee ) {
        this.adaptee = adaptee;
    }

    String clientMethod(String data) {

        // Data Conversion if required, XML -> JSON
       String special_data = convertToSpecialData(data);

       // make call to third party
       String response = adaptee.adapteeMethod(special_data);

       //(if require) Again convert back to original form JSON -> XML
       String responseData = convertToOriginalForm(response);
    }
}
```

## How to Implement

1. Make sure that you have at least two classes with incompatible interfaces:
   
   - A useful *service* class, which you can’t change (often 3rd-party, legacy or with lots of existing dependencies).
   - One or several *client* classes that would benefit from using the service class.

2. Declare the client interface and describe how clients communicate with the service.

3. Create the adapter class and make it follow the client interface. Leave all the methods empty for now.

4. Add a field to the adapter class to store a reference to the service object. The common practice is to initialize this field via the constructor, but sometimes it’s more convenient to pass it to the adapter when calling its methods.

5. One by one, implement all methods of the client interface in the adapter class. The adapter should delegate most of the real work to the service object, handling only the interface or data format conversion.

6. Clients should use the adapter via the client interface. This will let you change or extend the adapters without affecting the client code.
