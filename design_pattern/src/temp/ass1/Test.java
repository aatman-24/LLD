package 
temp.ass1;

public class Test {

    public static void main(String[] args) {

        StringManipulator convertToUpperCase = (String input) -> input.toUpperCase();
        StringManipulator reverseString = (String input) -> new StringBuilder(input).reverse().toString();
        StringManipulator strWithoutVowels = (String input) -> input.replaceAll("[aeiouAEIOUS]", "");
        StringManipulator appendProcessed = (String s) -> s + " - processed";
        System.out.println(convertToUpperCase.manipulate("abcde"));
    }
}
