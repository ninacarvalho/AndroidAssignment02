package com.nseaf.mycalculator;

public class Calculator {
    String numberString="0"; //Stores the current number as a string
    String detailsString=""; //Holds details of the last operation performed.
    long intNumber; //Represents the current number in integer form.
    double realNumber; //Represents the current number in double (real number) form.
    boolean isIntNumber=true; // Tracks if the current number is an integer.
    boolean numHasRadixPoint=false; // Tracks if the current number has a decimal point.
    long memoryInt=0; //Stores the integer value in memory.
    double memoryDouble=0.0; // Stores the double value in memory.
    boolean isIntMemory=true; //Tracks if the memory value is an integer.

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

/*
        if(numberString.length() < 12) {  // limit of 12 digits
            intNumber = intNumber * 10 + i;
            numberString = String.valueOf(intNumber);
            currentNumber = Double.parseDouble(numberString); // Update current number
            detailsString = "Clicked: " + i;
        }
        else
            detailsString="The number is too long..."; */
    }

    public void clearClicked() {
        numberString="0";
        detailsString="";
        intNumber=0;
        realNumber=0.0;
        isIntNumber=true;
        numHasRadixPoint=false;

        result = 0.0;
        currentNumber = 0.0;
        pendingOperation = "";
    }

    public void memPlusClicked() {
        if(isIntMemory){
            if(isIntNumber) {
                memoryInt += intNumber;
                detailsString = "Memory: " + memoryInt;
            }
            else {
                isIntNumber=false;
                memoryDouble = memoryInt + realNumber;
            }
        }
    }

    public void memMinusClicked() {
        if (isIntMemory) {
            if (isIntNumber) {
                memoryInt -= intNumber;
                detailsString = "Memory: " + memoryInt;
            } else {
                memoryDouble = memoryInt - realNumber;
                isIntNumber = false;
            }
        }
    }

    public void decimalPointClicked() {
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
            performPendingOperation();
            pendingOperation = "%";  // Set % as the pending operation
            clearPartial();  // Clear for the next input
        }
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

    public String getNumberString() {
        return numberString;
    }

    public String getDetailsString() {
        return detailsString;
    }
}
