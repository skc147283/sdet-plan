package junitTests;

//add this to build.gradle file to use junit
// testImplementation 'junit:junit:4.13.2'

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitDemo {


    //write a method that returns sum of two numbers and implement junit test case for it.

    public int sum(int a, int b) {
        return a + b;
    }


    @Test
    public void testSum() {
        JUnitDemo junitDemo = new JUnitDemo();
        int result = junitDemo.sum(2, 3);
        assertEquals(5, result);
    }



}
