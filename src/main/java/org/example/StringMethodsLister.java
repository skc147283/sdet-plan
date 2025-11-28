package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StringMethodsLister {
    public static void main(String[] args) {
        String outCsv = "string_methods.csv";
        if (args.length > 0) outCsv = args[0];

        try (BufferedWriter w = new BufferedWriter(new FileWriter(outCsv))) {
            // Header updated to match requested format: || Method Name|| return type || Arguments ||
            w.write("\"|| Method Name||\",return type,\"|| Arguments ||\"\n");
            Method[] methods = String.class.getDeclaredMethods();
            Arrays.sort(methods, Comparator.comparing(Method::getName)
                    .thenComparing(m -> Arrays.toString(m.getParameterTypes())));

            for (Method m : methods) {
                String name = m.getName();
                String returnType = m.getReturnType().getTypeName();
                String argsList = Arrays.stream(m.getParameterTypes())
                        .map(Class::getTypeName)
                        .collect(Collectors.joining(", "));

                // CSV-escape any double quotes and wrap argument field in quotes
                String safeArgs = argsList.replace("\"", "\"\"");
                safeArgs = "\"" + safeArgs + "\"";

                w.write(escapeCsv(name) + "," + escapeCsv(returnType) + "," + safeArgs + "\n");
            }

            System.out.println("Wrote: " + outCsv + " (" + methods.length + " methods)");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    private static String escapeCsv(String s) {
        if (s == null) return "";
        String out = s.replace("\"", "\"\"");
        if (out.contains(",") || out.contains("\n") || out.contains("\r") || out.contains("\"")) {
            out = "\"" + out + "\"";
        }
        return out;
    }
}

