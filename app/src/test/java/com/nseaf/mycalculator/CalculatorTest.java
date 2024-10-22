package com.nseaf.mycalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition() {
        calculator.processNumber(5);
        calculator.add();
        calculator.processNumber(3);
        calculator.equals();
        assertEquals("8.0", calculator.getNumberString());
    }

    @Test
    public void testSubtraction() {
        calculator.processNumber(10);
        calculator.subtract();
        calculator.processNumber(4);
        calculator.equals();
        assertEquals("6.0", calculator.getNumberString());
    }

    @Test
    public void testMultiplication() {
        calculator.processNumber(4);
        calculator.multiply();
        calculator.processNumber(2);
        calculator.equals();
        assertEquals("8.0", calculator.getNumberString());
    }

    @Test
    public void testDivision() {
        calculator.processNumber(20);
        calculator.divide();
        calculator.processNumber(5);
        calculator.equals();
        assertEquals("4.0", calculator.getNumberString());
    }

    @Test
    public void testDivisionByZero() {
        calculator.processNumber(20);
        calculator.divide();
        calculator.processNumber(0);
        calculator.equals(); // Attempt to divide by zero
        assertEquals("Error: Division by zero", calculator.getDetailsString());
    }

    @Test
    public void testMemoryFunctions() {
        calculator.processNumber(10);
        calculator.memPlus(); // Add to memory
        calculator.memRecall(); // Recall memory
        assertEquals("10.0", calculator.getNumberString()); // Check if memory recall is correct

        calculator.processNumber(5);
        calculator.memMinus(); // Subtract from memory
        calculator.memRecall(); // Recall memory
        assertEquals("5.0", calculator.getNumberString()); // Check if memory recall is correct

        calculator.memClear(); // Clear the memory
        calculator.memRecall(); // Recall memory
        assertEquals("0.0", calculator.getNumberString()); // Memory should be zero
    }

    @Test
    public void testClearFunctionality() {
        calculator.processNumber(25);
        calculator.clearClicked(); // First click, clears current number
        assertEquals("0", calculator.getNumberString()); // Current number should be cleared

        calculator.clearClicked(); // Second click, resets the calculator
        assertEquals("0", calculator.getNumberString()); // Current number should remain cleared
    }

    @Test
    public void testDecimalPointFunction() {
        calculator.processNumber(1);
        calculator.decimalPoint(); // Add decimal point
        calculator.processNumber(5); // Add another number
        assertEquals("1.5", calculator.getNumberString()); // Check if the output is correct
    }

    @Test
    public void testMaxNumberLimit() {
        for (int i = 0; i < 13; i++) {
            calculator.processNumber(9); // Attempt to add 12 digits
        }
        assertEquals("The number is too long...", calculator.getDetailsString()); // Check for max limit
    }

    @Test
    public void testPercentageAddition() {
        calculator.processNumber(50);
        calculator.add();
        calculator.processNumber(10);
        calculator.processPercentage();
        calculator.equals();
        assertEquals("55.0", calculator.getNumberString());  // 50 + 10% of 50 = 55
    }

    @Test
    public void testPercentageSubtraction() {
        calculator.processNumber(100);
        calculator.subtract();
        calculator.processNumber(20);
        calculator.processPercentage();
        calculator.equals();
        assertEquals("80.0", calculator.getNumberString());  // 100 - 20% of 100 = 80
    }

    @Test
    public void testPercentageMultiplication() {
        calculator.processNumber(200);
        calculator.multiply();
        calculator.processNumber(10);
        calculator.processPercentage();
        calculator.equals();
        assertEquals("20.0", calculator.getNumberString());  // 200 * 10% = 20
    }

    @Test
    public void testPercentageDivision() {
        calculator.processNumber(100);
        calculator.divide();
        calculator.processNumber(25);
        calculator.processPercentage();
        calculator.equals();
        assertEquals("400.0", calculator.getNumberString());  // 100 / 25% = 400
    }

    // Pressing subtract without a number should prepare for a negative number
    @Test
    public void testNegativeNumberAddition() {
        calculator.subtract();
        calculator.processNumber(5);
        calculator.add();
        calculator.processNumber(3);
        calculator.equals();
        assertEquals("-2.0", calculator.getNumberString());  // -5 + 3 = -2
    }

    @Test
    public void testNegativeNumberMultiplication() {
        calculator.subtract();
        calculator.processNumber(7);  // Input negative 7
        calculator.multiply();
        calculator.processNumber(2);
        calculator.equals();
        assertEquals("-14.0", calculator.getNumberString());  // -7 * 2 = -14
    }

    @Test
    public void testMemoryAndOperations() {
        calculator.processNumber(20);
        calculator.memPlus();  // Add 20 to memory
        calculator.processNumber(5);
        calculator.add();
        calculator.memRecall();  // Recall memory (20)
        calculator.equals();
        assertEquals("25.0", calculator.getNumberString());  // 5 + 20 = 25
    }

    @Test
    public void testStartingWithDecimal() {
        calculator.decimalPoint();  // Input starts with decimal
        calculator.processNumber(5);
        assertEquals("0.5", calculator.getNumberString());  // Check if output is correct
    }

    @Test
    public void testLongDecimalPrecision() {
        calculator.processNumber(1);
        calculator.decimalPoint();
        calculator.processNumber(23456789);  // Input a long decimal number
        assertEquals("1.23456789", calculator.getNumberString());  // Should handle up to 10 digits after decimal
    }
}
