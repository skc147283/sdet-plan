package org.example;

public class StringMethodsList {


    /*
    Here is a list of some of the most commonly used and important methods in the String class:
    charAt(int index): Returns the character at the specified index.
            compareTo(String anotherString): Compares two strings lexicographically.
    compareToIgnoreCase(String str): Compares two strings lexicographically, ignoring case differences.
            concat(String str): Concatenates the specified string to the end of this string.
            contains(CharSequence s): Checks if the string contains the specified sequence of characters.
    endsWith(String suffix): Tests if the string ends with the specified suffix.
            equals(Object anObject): Compares this string to the specified object.
            equalsIgnoreCase(String anotherString): Compares this string to another string, ignoring case considerations.
            getBytes(): Encodes this String into a sequence of bytes using the platform's default charset.
    indexOf(int ch): Returns the index within this string of the first occurrence of the specified character.
            indexOf(String str): Returns the index within this string of the first occurrence of the specified substring.
            isEmpty(): Returns true if, and only if, length() is 0.
    lastIndexOf(int ch): Returns the index within this string of the last occurrence of the specified character.
            length(): Returns the length of this string.
            replace(char oldChar, char newChar): Returns a new string resulting from replacing all occurrences of oldChar with newChar.
    replaceAll(String regex, String replacement): Replaces each substring of this string that matches the given regular expression with the given replacement.
            split(String regex): Splits this string around matches of the given regular expression.
    startsWith(String prefix): Tests if this string starts with the specified prefix.
    substring(int beginIndex): Returns a new string that is a substring of this string.
            substring(int beginIndex, int endIndex): Returns a new string that is a substring of this string.
            toCharArray(): Converts this string to a new character array.
    toLowerCase(): Converts all of the characters in this String to lowercase.
            toUpperCase(): Converts all of the characters in this String to uppercase.
            trim(): Returns a copy of the string, with leading and trailing whitespace omitted.
            valueOf(boolean b), valueOf(char c), valueOf(int i), etc.: Returns the string representation of the argument.
            repeat(int count) (Java 11+): Returns a string whose value is the concatenation of this string count times.
            isBlank() (Java 11+): Returns true if the string is empty or contains only white space codepoints, otherwise false.
    strip(), stripLeading(), stripTrailing() (Java 11+): Methods for removing leading/trailing whitespace, including Unicode whitespace.
    lines() (Java 11+): Returns a stream of lines extracted from this string, separated by line terminators.

    */


    //Write main method to print all above methods
    public static void main(String[] args) {
        System.out.println("Commonly Used String Methods in Java:");
        System.out.println("1. charAt(int index)");
        System.out.println("2. compareTo(String anotherString)");
        System.out.println("3. compareToIgnoreCase(String str)");
        System.out.println("4. concat(String str)");
        System.out.println("5. contains(CharSequence s)");
        System.out.println("6. endsWith(String suffix)");
        System.out.println("7. equals(Object anObject)");
        System.out.println("8. equalsIgnoreCase(String anotherString)");
        System.out.println("9. getBytes()");
        System.out.println("10. indexOf(int ch)");
        System.out.println("11. indexOf(String str)");
        System.out.println("12. isEmpty()");
        System.out.println("13. lastIndexOf(int ch)");
        System.out.println("14. length()");
        System.out.println("15. replace(char oldChar, char newChar)");
        System.out.println("16. replaceAll(String regex, String replacement)");
        System.out.println("17. split(String regex)");
        System.out.println("18. startsWith(String prefix)");
        System.out.println("19. substring(int beginIndex)");
        System.out.println("20. substring(int beginIndex, int endIndex)");
        System.out.println("21. toCharArray()");
        System.out.println("22. toLowerCase()");
        System.out.println("23. toUpperCase()");
        System.out.println("24. trim()");
        System.out.println("25. valueOf(boolean b), valueOf(char c), valueOf(int i), etc.");
        System.out.println("26. repeat(int count) (Java 11+)");
        System.out.println("27. isBlank() (Java 11+)");
        System.out.println("28. strip(), stripLeading(), stripTrailing() (Java 11+)");
        System.out.println("29. lines() (Java 11+)");


    }

    //End of main method

    // can we write junit test for above methods as a demoo ? yes we can write junit test for above methods as a demo.
    // But since these are standard library methods, testing them would primarily serve as examples of usage rather than validating functionality.
    // Here's a simple JUnit test class demonstrating how to use some of these String methods.






}
