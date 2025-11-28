package org.example;


/**
 * This class demonstrates the immutability of Java Strings.
 * It shows that modifying a string results in a new string object,
 * while the original string remains unchanged.
 *
 * Reason : For Security Purpose
 * Strings access sensitive data like database, usernames, passwords, network connections, etc.
 * we want to avoid any accidental or intentional modification of such data.
 *
 */

public class StringImmutableDemo {


    public static void main(String[] args) {

        String str = "Initial String";
        System.out.println(str);
        System.out.println("String address: " + System.identityHashCode(str));

        // Attempt to modify the string
        str.replace(str,"Modified String");
        System.out.println("After replace attempt: " + str);
        // print str address
        System.out.println("String address: " + System.identityHashCode(str));

        //Reassigning to a new string
        str = str.concat(" - Appended Text");
        System.out.println("After reassignment: " + str);
        System.out.println("String address: " + System.identityHashCode(str));



    }


}
