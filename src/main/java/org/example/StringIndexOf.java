package org.example;

public class StringIndexOf {

    public static void main(String[] args) {
        String str = "Hello, welcome to the world of Java programming.";
        char ch = 'g';
        int index = str.indexOf(ch);

        if (index != -1) {
            System.out.println("The character '" + ch + "' is found at index: " + index);
        } else {
            System.out.println("The character '" + ch + "' is not found in the string.");
        }
    }

}
