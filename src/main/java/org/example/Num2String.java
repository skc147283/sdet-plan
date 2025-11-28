package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Num2String {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome! \n");


        int inputNumber = 40899756;
        String ans= Integer.toString(inputNumber);
        System.out.println("Converted String with Integer.toString : " + ans);
        System.out.println("is String "+ ans.getClass());

        int inputNumber2 = -12345;
        String ans2 = String.valueOf(inputNumber2);
        System.out.println("Converted String with String.valueOf : " + ans2);


    }
}