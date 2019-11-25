package com.example.myapplication;

class SimpleCalculator {

    CalculatorListener listener;
    Integer operand1;
    Integer operand2;
    String operator;


    public SimpleCalculator(CalculatorListener listener) {
        this.listener = listener;
    }

    public void setOperand(int value) {
        if (operand1 == null)
            operand1 = value;
        else {
            operand2 = value;
            if (operator.equals("+"))
                operand1 += operand2;
            else
                operand1 -= operand2;
            listener.onResultCalculated(operand1);
        }
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void reset() {
        operand1 = null;
        operand2 = null;
        operator = null;
    }
}