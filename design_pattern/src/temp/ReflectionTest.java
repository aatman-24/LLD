package 
temp;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        Class birdClass = Eagle.class;
//        System.out.println(birdClass.getName());
//        System.out.println(Arrays.stream(birdClass.getDeclaredMethods()).toList());
//
//        Method[] methods = birdClass.getDeclaredMethods();
//        for(Method method: methods) {
//            System.out.println(method.getName());
//        }

//        Object birdObj = birdClass.newInstance();
        Method flyMethod = birdClass.getMethod("canFly", String.class);


//        for (Field field : birdClass.getFields()) {
//            System.out.println(field.getName());
//            System.out.println(field.getModifiers());
//        }

        Object birdObj = birdClass.newInstance();
        Field field = birdClass.getDeclaredField("name");
        field.setAccessible(true);
        field.set(birdObj, "NAME");
        flyMethod.invoke(birdObj, "MEEE");






    }
}
