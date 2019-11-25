package com.example.myapplication;

import android.view.View;
import android.widget.Button;

class Presenter implements View.OnClickListener, CalculatorListener {

    CalculatorView calculatorView;
    SimpleCalculator calculator;

    boolean operatorExpected = false;
    boolean isFirstDigit = true;

    public Presenter(CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
        calculator = new SimpleCalculator(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String symbol = btn.getText().toString();
        if (operatorExpected) {
            if (symbol.equals("+") || symbol.equals("-")) {
                calculator.setOperand(Integer.parseInt(calculatorView.getNumber()));
                calculator.setOperator(symbol);
                isFirstDigit = true;
                return;
            }
        }
        if (symbol.toLowerCase().equals("clear")) {
            calculatorView.setNumber("");
            operatorExpected = false;
            isFirstDigit = true;
            calculator.reset();
        } else {
            if (isFirstDigit) {
                calculatorView.setNumber(symbol);
                isFirstDigit = false;
                operatorExpected = true;
            } else {
                calculatorView.setNumber(calculatorView.getNumber() + symbol);
            }
        }
    }

    @Override
    public void onResultCalculated(int result) {
        calculatorView.setNumber(result + "");
    }
}