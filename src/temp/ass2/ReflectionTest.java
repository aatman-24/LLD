package 
temp.ass2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        Class personClass = Person.class;
        System.out.println("List of Fields with their names: ");
        for(Field field: personClass.getDeclaredFields()) {
            System.out.println("Field name: " + field.getName());
            System.out.println("Field modifiers: " + Modifier.toString(field.getModifiers()));
        }
        System.out.println("\n\n");
        System.out.println("List of Methods with their names: ");
        for(Method method: personClass.getDeclaredMethods()) {
            System.out.println("Method name: " + method.getName());
            System.out.println("Method modifiers: " + Modifier.toString(method.getModifiers()));
        }
        System.out.println("\n\n");
        System.out.println("Superclass of Person is: " + personClass.getSuperclass().getName());


        System.out.println("\n\n");
        System.out.println("\n\n");

       Object person = personClass.newInstance();

       Field nameField = personClass.getDeclaredField("name");
       nameField.setAccessible(true);
       nameField.set(person, "Aatman");

        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(person, 24);

        Method method = personClass.getDeclaredMethod("greet");
        method.setAccessible(true);
        method.invoke(person);
    }
}
