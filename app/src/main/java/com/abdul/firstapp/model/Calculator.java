package com.abdul.firstapp.model;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity {

    public static int add(int a, int b){
        return a + b;
    }

    public static int multiply(int a, int b){
        return a * b;
    }

    public static int subtract(int a, int b){
        return a - b;
    }

    public static int divide(int a, int b){
        return a / b;
    }

    public static int modulo(int a, int b) {return a % b;}
}
