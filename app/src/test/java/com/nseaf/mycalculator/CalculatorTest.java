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
}
