### Design Pattern: Builder Design Pattern

---

> **Builder** is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.



Mainly Used when creating objects involves many complex steps, or facing the telescoping construction pattern or consturction involved numerous optional parameter or have different representation of objects. 



So in this pattern, we have one `Product`, `Builder` and `Director`. 



Product is what we are creating, Builder is building that Product and Director is guilde the Builder to follow the specific way(flow). 



![](/home/aatman/snap/marktext/9/.config/marktext/images/2024-10-01-21-26-35-image.png)



Here, ConcreteBuilders are different representation of Product that's why need different Builder(who actually override/implement those spcific behaviour). 



Client needs to handle which Builder class they are passing to Director constructor... based on that it get the products. 



Example

---



```java
// Product
public class Student {

    String firstname;
    String latname;
    String rollNum;
    String age;
    String surname;
    String fathername;
    String subject;
    
    public Student(StudentBuilder studentBuilder) {
        this.firstname = studentBuilder.firstname;
        this.latname = studentBuilder.latname;
        this.age = studentBuilder.age;
        this.fathername = studentBuilder.fathername;
        this.rollNum = studentBuilder.rollNum;
        this.surname = studentBuilder.surname;
        this.subject = studentBuilder.subject;
    }
    
    public String getSubject() {
        return subject;
    }
}

// Builder
public abstract class StudentBuilder {

    String firstname;
    String latname;
    String rollNum;
    String age;
    String surname;
    String fathername;
    protected String subject;


    public StudentBuilder() {
    }

    public StudentBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public StudentBuilder setLatname(String latname) {
        this.latname = latname;
        return this;
    }

    public StudentBuilder setRollNum(String rollNum) {
        this.rollNum = rollNum;
        return this;
    }

    public StudentBuilder setAge(String age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public StudentBuilder setFathername(String fathername) {
        this.fathername = fathername;
        return this;
    }

    abstract public StudentBuilder setSubject();
    
    // creation of new product happen in Builder. 
    public Student build() {
        return new Student(this);
    }
}

// Different representation of StudentBuilder, which use the same Constructor
public class CSStudentBuilder extends StudentBuilder{
    @Override
    public StudentBuilder setSubject() {
        this.subject = "CSWithMojo";
        return this;
    }
}

public class MBAStudentBuilder extends StudentBuilder {
    @Override
    public StudentBuilder setSubject() {
        this.subject = "MBAChaiWala";
        return this;
    }
}

// Director handle which studentBuilder to use. 
public class Director {

    StudentBuilder studentBuilder;

    public Director(StudentBuilder studentBuilder) {
        this.studentBuilder = studentBuilder;
    }

    public StudentBuilder createStudent() {

        if(studentBuilder instanceof CSStudentBuilder) {
            return createCSStudent();
        }

        return createMBAStudent();
    }

    public StudentBuilder createMBAStudent() {
        return studentBuilder.setSubject();
    }

    public StudentBuilder createCSStudent() {
        return studentBuilder.setSubject();
    }
}


public class BuilderDesignClient {

    public static void run() {

        // See director handling which Builder to use and based on that it get the product. 
        Director director = new Director(new CSStudentBuilder());
        Student aatman = director.createStudent().setAge("24").setFathername("Jitendrabhai").setFirstname("Aatman").setRollNum("25").build();

        Director director2 = new Director(new MBAStudentBuilder());
        Student mitul = director2.createStudent().setAge("24").setFathername("Jitendrabhai").setFirstname("Mitul").setRollNum("25").build();

        System.out.println(aatman.getSubject());
        System.out.println(mitul.getSubject());

    }
}
```




