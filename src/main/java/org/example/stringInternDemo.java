package org.example;

public class stringInternDemo  {


    public static void main(String[] args) {

        String str1 = new String("Hello World");
        String str2 = str1.intern();

        String str3 = "Hello World";

        System.out.println("str1 address: " + System.identityHashCode(str1));
        System.out.println("str2 address: " + System.identityHashCode(str2));
        System.out.println("str3 address: " + System.identityHashCode(str3));

        System.out.println("str1 == str2: " + (str1 == str2)); // false
        System.out.println("str2 == str3: " + (str2 == str3)); // true

    }
}
