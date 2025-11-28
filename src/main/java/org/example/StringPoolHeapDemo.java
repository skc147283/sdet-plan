package org.example;

public class StringPoolHeapDemo     {

     public static void main(String[] args) {

         //String created using String Literal
         String str1ConstantPool = "Hello";
         String str2ConstantPool = "Hello";
         //print memory addresses
            System.out.println("String Literal {Hello} Memory Addresses:");
            System.out.println("str1ConstantPool address: " + System.identityHashCode(str1ConstantPool));
            System.out.println("str2ConstantPool address: " + System.identityHashCode(str2ConstantPool));
            System.out.println("Are both string literals pointing to same memory? " + (str1ConstantPool == str2ConstantPool));
         System.out.println(" Compare string value with equals method  " + (str1ConstantPool.equals( str2ConstantPool )));

         String str1NewHeap = new String("Hello");
         String str2NewHeap = new String("Hello");
        // prove both are different objects in heap memory
            System.out.println("\nString {Hello} Created using 'new' Keyword Memory Addresses:");
            System.out.println("str1NewHeap address: " + System.identityHashCode(str1NewHeap));
            System.out.println("str2NewHeap address: " + System.identityHashCode(str2NewHeap));
            System.out.println("Are both 'new' strings pointing to same memory : check with == operator ? " + (str1NewHeap == str2NewHeap));
         System.out.println(" Compare string value with equals method  " + (str1NewHeap.equals( str2NewHeap )));


     }


}
