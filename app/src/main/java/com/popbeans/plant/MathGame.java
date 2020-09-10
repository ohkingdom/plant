package com.popbeans.plant;

import java.util.Random;

public class MathGame {

    private int difficulty = 1;
    private int constant;
    private String operator;
    private int variable;
    private int sum;

    public MathGame() {
        request();
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void request() {
        // Generate Random Values
        constant = new Random().nextInt(50 + 1);
        variable = new Random().nextInt(50 + 1);
        // Use Difficulty to Select Equation Difficulty
        int i = new Random().nextInt(2);
        if (difficulty > 1) {
            i = new Random().nextInt(2 + 2);
        }
        switch (i) {
            case 0:
                operator = "+";
                sum = (constant = new Random().nextInt(50 + 1)) + (variable = new Random().nextInt(50 + 1));
                break;
            case 1:
                operator = "-";
                sum = (constant = new Random().nextInt(50 + 1)) - (variable = new Random().nextInt(50 + 1));
                break;
            case 3:
                operator = "+";
                sum = (constant = new Random().nextInt(75 + 1)) + (variable = new Random().nextInt(75 + 1));
                break;
            case 4:
                operator = "-";
                sum = (constant = new Random().nextInt(75 + 1)) - (variable = new Random().nextInt(75 + 1));
        }
    }

    public boolean answer(int answer) {
        if (answer == variable) {
            request();
            return true;
        }
        return false;
    }

    public String getConstant() {
        return String.valueOf(constant);
    }

    public String getOperator() {
        return operator;
    }

    public String getSum() {
        return String.valueOf(sum);
    }

    @Override
    public String toString() {
        return constant + " " + operator + " " + "?" + " " + "=" + " " + sum;
    }

}
