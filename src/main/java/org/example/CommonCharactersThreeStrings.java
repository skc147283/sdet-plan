package org.example;

public class CommonCharactersThreeStrings   {


    public static String commonChars(String str1, String str2, String str3) {
        StringBuilder common = new StringBuilder();
        for (char c : str1.toCharArray()) {
            if (str2.indexOf(c) != -1 && str3.indexOf(c) != -1 && common.indexOf(String.valueOf(c)) == -1) {
                common.append(c);
            }
        }
        return common.toString();
    }

    public static void main(String[] args) {
        String str1 = "abcdefgh";
        String str2 = "bcdxyz";
        String str3 = "cdefghij";

        String result = commonChars(str1, str2, str3);
        System.out.println("Common characters: " + result); // Output: "cd"
    }

}
