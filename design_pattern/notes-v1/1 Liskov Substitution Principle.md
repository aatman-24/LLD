# Liskov Substitution Principle Example

> Principle state that, Child class object should substitute parent class object without affecting the correctness of the program.

---

### Violation of Liskov Principle:

CASE 1: Vehicle class have two child class and code is working as expected. 

Parent Class

    class Vehicle {
    
        int getNumberOfWheels() {
            return 2;
        }
    
        boolean hasEngine() {
            return true;
        }
    }

Child class: MotorCycle

    class MotorCycle extends Vehicle {
    
    }

Child class: Car

    class Car extends Vehicle {
    
        @override
        int getNumberOfWheels() {
            return 4;
        }
    }

Main Class

    class Main {
    
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new MotorCycle());
        vehicles.add(new Car());
    
    
        for(Vehicle vehicle: vehicles) {
            System.out.println(vehicle.hasEngine().toString())
        }
    }

Explanation: Client code(Main class) you substitute subclass objects and during fetching 
information it is working fine. No issue. 

---

#### CASE 2: New requirement coming up, and you needed one more child class named Bicycle. Let's see how it breaks our code to understand what problem is solved by this principle.

Parent Class

    class Vehicle {
    
        int getNumberOfWheels() {
            return 2;
        }
    
        boolean hasEngine() {
            return true;
        }
    }

Child class: MotorCycle

    class MotorCycle extends Vehicle {
    
    }

Child class: Car

    class Car extends Vehicle {
    
        @override
        int getNumberOfWheels() {
            return 4;
        }
    }

Child class: Bicycle (Newly added class)

    class Bicycle extends Vehicle {
    
        @override
        boolean hasEngine() {
            return null;        // doesn't have engine, so we are removing functionality which is red flag. 
        }
    
    }

Main class

    class Main {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new MotorCycle());
        vehicles.add(new Car());
        vehicles.add(new Bicycle());
    
    
        for(Vehicle vehicle: vehicles) {
            System.out.println(vehicle.hasEngine().toString()) // Throw null pointer Exception. (bicycle return null which cannot be converted to string)
        }
    }

Problem: We added new child class, which doesn't have parent class behaviour, so we removed that feature by returning null. Which was not accepted. 

--- 

### Solution using Liskov principle :

#### CASE 3: Applying the Liskov principle to refactor the code such way that it will not break the code. If we are considering bicycle as vehicle, we need to first refactor parent class **Vehicle** make it more generic so Bicycle can extend it.

STEP-1: Parent Class

    class Vehicle {
    
        int getNumberOfWheels() {
            return 2;
        }
    }

STEP-2: Introduced new class where we can put removed feature from Vehicle. 

    class EngineVehicle extends Vehicle {
    
        boolean hasEngine() {
            return true;
        }
    }

STEP-3: Bicycle extend the Vehicle bcz it doesn't have engine now. 

    class Bicycle extends Vehicle {
    
    }

STEP-4: MotorCycle & Car extends the EngineVehicle, not Vehicle. 

    class MotorCycle extends EngineVehicle {
    
    }
    
    class Car extends EngineVehicle {
    
        @override
        int getNumberOfWheels() {
            return 4;
        }
    }

STEP-5: BicycleVehicle extends Bicycle. Here we can also use Bicycle directly. 

    class BicycleVehicle extends Bicycle {
    
        @override
        boolean hasEngine() {
            return null;
        }
    }

Client Code: 

    class Main {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new MotorCycle());
        vehicles.add(new Car());
        vehicles.add(new BicycleVehicle());
    
        // Throw null pointer Exception. 
        for(Vehicle vehicle: vehicles) {
            System.out.println(vehicle.hasEngine().toString()) // vehicle has only one method, so we can not call the hasEngine() which restrict to break the code.
        }
    }

Comments: In case if you want to check with hasEngine() method, you shouldn't add the bicycle in that list and during the iteration you can easily use the EngineVehicle as superclass instead of the Vehicle. 
