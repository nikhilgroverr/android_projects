package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        // Buttons
        Button btnClear = findViewById(R.id.btnClear);
        Button btnSign = findViewById(R.id.btnSign);
        Button btnPercent = findViewById(R.id.btnPercent);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnSeven = findViewById(R.id.btnSeven);
        Button btnEight = findViewById(R.id.btnEight);
        Button btnNine = findViewById(R.id.btnNine);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnFour = findViewById(R.id.btnFour);
        Button btnFive = findViewById(R.id.btnFive);
        Button btnSix = findViewById(R.id.btnSix);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);
        Button btnThree = findViewById(R.id.btnThree);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnZero = findViewById(R.id.btnZero);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnEquals = findViewById(R.id.btnEquals);

        // Set onClickListeners
        btnClear.setOnClickListener(v -> clear());
        btnSign.setOnClickListener(v -> toggleSign());
        btnPercent.setOnClickListener(v -> appendPercent());
        btnDivide.setOnClickListener(v -> setOperator("/"));
        btnMultiply.setOnClickListener(v -> setOperator("*"));
        btnMinus.setOnClickListener(v -> setOperator("-"));
        btnAdd.setOnClickListener(v -> setOperator("+"));
        btnEquals.setOnClickListener(v -> calculateResult());

        // Set number buttons
        btnSeven.setOnClickListener(v -> appendToDisplay("7"));
        btnEight.setOnClickListener(v -> appendToDisplay("8"));
        btnNine.setOnClickListener(v -> appendToDisplay("9"));
        btnFour.setOnClickListener(v -> appendToDisplay("4"));
        btnFive.setOnClickListener(v -> appendToDisplay("5"));
        btnSix.setOnClickListener(v -> appendToDisplay("6"));
        btnOne.setOnClickListener(v -> appendToDisplay("1"));
        btnTwo.setOnClickListener(v -> appendToDisplay("2"));
        btnThree.setOnClickListener(v -> appendToDisplay("3"));
        btnZero.setOnClickListener(v -> appendToDisplay("0"));

        // Handle dot (.)
        btnDot.setOnClickListener(v -> appendDot());
    }

    // Method to append numbers to the display
    private void appendToDisplay(String number) {
        currentInput += number;
        tvDisplay.setText(currentInput);
    }

    // Method to append a dot to the display
    private void appendDot() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            tvDisplay.setText(currentInput);
        }
    }

    // Method to set the operator
    private void setOperator(String operator) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            this.operator = operator;
            currentInput = "";
        }
    }

    // Method to calculate the result
    private void calculateResult() {
        if (!previousInput.isEmpty() && !currentInput.isEmpty() && !operator.isEmpty()) {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        tvDisplay.setText("Error");
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            tvDisplay.setText(currentInput);
            operator = "";
            previousInput = "";
        }
    }

    // Method to clear the display
    private void clear() {
        currentInput = "";
        previousInput = "";
        operator = "";
        tvDisplay.setText("0");
    }

    // Method to toggle the sign (positive/negative)
    private void toggleSign() {
        if (!currentInput.isEmpty()) {
            if (currentInput.startsWith("-")) {
                currentInput = currentInput.substring(1);
            } else {
                currentInput = "-" + currentInput;
            }
            tvDisplay.setText(currentInput);
        }
    }

    // Method to append percent
    private void appendPercent() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            value /= 100;
            currentInput = String.valueOf(value);
            tvDisplay.setText(currentInput);
        }
    }
}
