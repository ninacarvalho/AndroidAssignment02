package com.nseaf.mycalculator;

public class Calculator {
    private String numberString = "0";   // Stores the current number as a string
    private String detailsString = "";   // Holds details of the last operation performed.
    private long intNumber;              // Represents the current number in integer form.
    private boolean isIntNumber = true;  // Tracks if the current number is an integer.
    private boolean numHasRadixPoint = false;  // Tracks if the current number has a decimal point.

    private double memory = 0.0;         // Stores the double value in memory.
    private boolean clearOnce = false;   // Tracks if the clear button was clicked once
    private double result = 0.0;         // Stores the result of the pending operation.
    private String pendingOperation = ""; // Stores the current pending operation.
    private double currentNumber = 0.0;  // Holds the current number being processed.

    private boolean isNextNegative = false;  // Tracks if the next number should be negative

    public Calculator() {}

    public void processNumber(int i) {
        if (numberString.length() >= 12) { // limit of 12 digits
            detailsString = "The number is too long...";
            return;
        }
        // If the number already has a decimal point
        if (numHasRadixPoint) {
            numberString += i;  // Append the number after the decimal
        } else {
            intNumber = intNumber * 10 + i;
            numberString = String.valueOf(intNumber);
        }

        currentNumber = Double.parseDouble(numberString); // Update current number

        if (isNextNegative) {
            currentNumber = -currentNumber;
            numberString = String.valueOf(currentNumber);  // Update display string
            isNextNegative = false;  // Reset flag
        }

        detailsString = "Clicked: " + i;
        resetClearFlag();
    }

    // Process Pi input
    public void processPi() {
        currentNumber = Math.PI;  // Assign Pi value to the current number
        numberString = String.format("%.10f", currentNumber);  // Format Pi to 10 decimal places
        detailsString = "π";  // Show Pi symbol in the details
        resetClearFlag();
    }

    // Clear button handler
    public void clearClicked() {
        if (!clearOnce) {
            clearPartial();
            detailsString = "Cleared current number";
            clearOnce = true;  // Set flag for next click
        } else {
            resetCalculator();
            detailsString = "Calculator reset";
            clearOnce = false;  // Reset flag after full clear
        }
    }

    public void memPlus() {
        memory += currentNumber;
        detailsString = "Memory: " + memory;
        clearPartial();
    }

    public void memMinus() {
        memory -= currentNumber;
        detailsString = "Memory: " + memory;
        clearPartial();
    }

    public void memClear() {
        // Clear the memory
        memory = 0.0;
        numberString = "";
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
            detailsString = "Decimal point added";
        }
    }

    public void add() {
        storePendingOperation("+");
    }

    public void subtract() {
        if (numberString.equals("0") || numberString.equals("")) {
            isNextNegative = true;
            detailsString = "-";
        } else {
            storePendingOperation("-");
        }
    }

    public void multiply() {
        storePendingOperation("*");
    }

    public void divide() {
        storePendingOperation("/");
    }

    public void equals() {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
            pendingOperation = ""; // Reset operation after calculation
            if(detailsString.toLowerCase().contains("error")) {
                return;
            }
            numberString = String.valueOf(result); // Display result as the new current number
            detailsString = "Result: " + result;
            currentNumber = result;
        }
    }

    // Calculate exponent (e^x)
    public void calculateExp() {
        result = Math.exp(currentNumber);  // Calculate e^x
        detailsString = "e^" + currentNumber + " = " + result;
        numberString = String.valueOf(result);
        clearPartial();  // Prepare for next input
    }

    public void processPercentage() {
        if (pendingOperation.isEmpty()) {
            result = currentNumber / 100;
            currentNumber = result;
        } else {
            handlePercentage();
        }
        pendingOperation = "";
        numberString = String.valueOf(result);
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
        }
        currentNumber = result;
    }

    private void storePendingOperation(String operation) {
        if (!pendingOperation.isEmpty()) {
            performPendingOperation();
        } else {
            result = currentNumber;
        }

        pendingOperation = operation;
        clearPartial();
        detailsString = "Stored for " + operation;
    }

    private void resetCalculator() {
        result = 0.0;
        pendingOperation = "";
        memory = 0.0;
        clearPartial();
    }

    public void clearPartial() {
        numberString="0";
        intNumber=0;
        numHasRadixPoint=false;
        currentNumber = 0.0;
    }

    private void resetClearFlag() {
        clearOnce = false;
    }

    // Getters for UI
    public String getNumberString() {
        return numberString;
    }

    public String getDetailsString() {
        return detailsString;
    }
}
