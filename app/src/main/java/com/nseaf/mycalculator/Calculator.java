package com.nseaf.mycalculator;

public class Calculator {
    String numberString="0"; //Stores the current number as a string
    String detailsString=""; //Holds details of the last operation performed.
    long intNumber; //Represents the current number in integer form.
    double realNumber; //Represents the current number in double (real number) form.
    boolean isIntNumber=true; // Tracks if the current number is an integer.
    boolean numHasRadixPoint=false; // Tracks if the current number has a decimal point.
    double memory = 0.0; // Stores the double value in memory.
    boolean isIntMemory=true; //Tracks if the memory value is an integer.

    boolean clearOnce = false;  // Tracks if clear button was clicked once

    private double result = 0.0; // Stores the result of the pending operation.
    private String pendingOperation = ""; //Stores the current pending operation.
    private double currentNumber = 0.0; //Holds the current number being processed for calculation.

    public Calculator() {
    }

    public void processNumber(int i) {
        if (numberString.length() >= 12) { // limit of 12 digits
            detailsString = "The number is too long...";
            return;
        }
        if (numHasRadixPoint) {  // If the number already has a decimal point
            numberString += i;  // Append the number after the decimal
            realNumber = Double.parseDouble(numberString);
            currentNumber = realNumber;
        } else {
            intNumber = intNumber * 10 + i;
            numberString = String.valueOf(intNumber);
            currentNumber = Double.parseDouble(numberString); // Update current number
        }
        detailsString = "Clicked: " + i;
        resetClearFlag();
    }

    public void clearClicked() {
        if (!clearOnce) {
            // First click: clear the current displayed number
            numberString = "0";
            intNumber = 0;
            realNumber = 0.0;
            isIntNumber = true;
            numHasRadixPoint = false;
            currentNumber = 0.0;
            detailsString = "Cleared current number";
            clearOnce = true;  // Set flag for next click
        } else {
            // Second consecutive click: reset everything
            resetCalculator();
            detailsString = "Calculator reset";
            clearOnce = false;  // Reset flag after full clear
        }
    }

    private void resetCalculator() {
        result = 0.0;
        pendingOperation = "";
        memory = 0.0;
        isIntMemory = true;
    }

    public void memPlus() {
        memory += currentNumber;
        detailsString = "Memory: " + memory;
    }

    public void memMinus() {
        memory -= currentNumber;
        detailsString = "Memory: " + memory;
    }

    public void memClear() {
        // Clear the memory
        memory = 0.0;
        detailsString = "Memory cleared";
    }

    public void memRecall() {
        // Recall the memory and set it as the current number
        currentNumber = memory;
        numberString = String.valueOf(currentNumber);
        detailsString = "Recalled Memory: " + memory;
    }

    public void decimalPoint() {
        if (!numHasRadixPoint) {
            numberString += ".";
            numHasRadixPoint = true;
            isIntNumber = false;
            detailsString = "Decimal point added";
        }
    }

    public void clearPartial() {
        numberString="";
        intNumber=0;
        realNumber=0.0;
        isIntNumber=true;
        numHasRadixPoint=false;

        currentNumber = 0.0;
    }

    public void add() {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
        } else {
            result = currentNumber;
        }
        detailsString = "Stored for addition: " + result;
        pendingOperation = "+";
        clearPartial(); // Clear for the next input
    }

    public void subtract() {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
        } else {
            result = currentNumber;
        }
        detailsString = "Stored for subtraction: " + result;
        pendingOperation = "-";
        clearPartial();
    }

    public void multiply() {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
        } else {
            result = currentNumber;
        }
        detailsString = "Stored for myltiplying: " + result;
        pendingOperation = "*";
        clearPartial();
    }

    public void divide() {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
        } else {
            result = currentNumber;
        }
        detailsString = "Stored for division: " + result;
        pendingOperation = "/";
        clearPartial();
    }

    public void equals() {
        // Perform the pending operation and show the result.
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
            pendingOperation = ""; // Reset operation after calculation
            numberString = String.valueOf(result); // Display result as the new current number
            detailsString = "Result: " + result;
        }
    }

    public void processPercentage() {
        if (!pendingOperation.isEmpty()) {
            handlePercentage();
        } else {
            pendingOperation = "%";
            handlePercentage();
        }
        pendingOperation = ""; // Reset operation after calculation
        numberString = String.valueOf(result); // Display result as the new current number
        detailsString = "Result: " + result;
    }

    private void performPendingOperation() {
        switch (pendingOperation) {
            case "+":
                result += currentNumber;
                break;
            case "-":
                result -= currentNumber;
                break;
            case "*":
                result *= currentNumber;
                break;
            case "/":
                if (currentNumber != 0) {
                    result /= currentNumber;
                } else {
                    detailsString = "Error: Division by zero";
                }
                break;
            case "%":
                handlePercentage();
                break;
            default:
                detailsString = "Unknown operation";
                break;
        }
    }

    private void handlePercentage() {
        switch (pendingOperation) {
            case "+":
                result += result * (currentNumber / 100);
                break;
            case "-":
                result -= result * (currentNumber / 100);
                break;
            case "*":
                result *= (currentNumber / 100);
                break;
            case "/":
                if (currentNumber != 0) {
                    result /= (currentNumber / 100);
                } else {
                    detailsString = "Error: Division by zero";
                }
                break;
            default:
                //simple percentage
                result = currentNumber / 100;
                break;
        }
    }

    private void resetClearFlag() {
        clearOnce = false; // Reset the flag if any other button is clicked
    }

}
