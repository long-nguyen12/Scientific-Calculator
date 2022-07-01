package com.example.sciencecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }

    private String replaceFormula(String delim, String source, String des) {
        Expression exp = new Expression(source);
        String newSource = source.replaceAll(delim, des);
        Log.e("SRC", source + " " + newSource);
        return newSource;
    }

    private void updateText(String strToAdd) {
        String olsStr = display.getText().toString();
        String replaceStr = "";

        if (olsStr.contains("sin-1")) {
            replaceStr = replaceFormula("sin-1", olsStr, "sin<sup>-1</sup>");
        } else if (olsStr.contains("cos-1")) {
            replaceStr = replaceFormula("cos-1", olsStr, "cos<sup>-1</sup>");
        } else if (olsStr.contains("tan-1")) {
            replaceStr = replaceFormula("tan-1", olsStr, "tan<sup>-1</sup>");
        } else {
            replaceStr = olsStr;
        }

        String concatText = "";
        if (strToAdd.contains("sin-1")) {
            strToAdd = replaceFormula("sin-1", strToAdd, "sin<sup>-1</sup>(");
        } else if (strToAdd.contains("cos-1")) {
            strToAdd = replaceFormula("cos-1", strToAdd, "cos<sup>-1</sup>(");
        } else if (strToAdd.contains("tan-1")) {
            strToAdd = replaceFormula("tan-1", strToAdd, "tan<sup>-1</sup>(");
        }
        concatText = replaceStr + strToAdd;
        Log.e("concatText", concatText);
        display.setText(Html.fromHtml(concatText));
    }

    public void zeroBTNPush(View view) {
        updateText(getResources().getString(R.string.zero));
    }

    public void oneBTNPush(View view) {
        updateText(getResources().getString(R.string.one));
    }

    public void twoBTNPush(View view) {
        updateText(getResources().getString(R.string.two));
    }

    public void threeBTNPush(View view) {
        updateText(getResources().getString(R.string.three));
    }

    public void fourTNPush(View view) {
        updateText(getResources().getString(R.string.four));
    }

    public void fiveBTNPush(View view) {
        updateText(getResources().getString(R.string.five));
    }

    public void sixBTNPush(View view) {
        updateText(getResources().getString(R.string.six));
    }

    public void sevenBTNPush(View view) {
        updateText(getResources().getString(R.string.seven));
    }

    public void eightBTNPush(View view) {
        updateText(getResources().getString(R.string.eight));
    }

    public void nineBTNPush(View view) {
        updateText(getResources().getString(R.string.nine));
    }

    public void divideBTNPush(View view) {
        updateText(getResources().getString(R.string.divide));
    }

    public void multiplyBTNPush(View view) {
        updateText(getResources().getString(R.string.multiply));
    }

    public void addBTNPush(View view) {
        updateText(getResources().getString(R.string.add));
    }

    public void subtractBTNPush(View view) {
        updateText(getResources().getString(R.string.subtract));
    }

    public void clearBTNPush(View view) {
        display.setText("");
        previousCalculation.setText("");
    }

    public void parOpenBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesOpen));
    }

    public void parCloseBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesClose));
    }

    public void plusMinusBTNPush(View view) {
        updateText("");
    }

    public void decimalBTNPush(View view) {
        updateText(getResources().getString(R.string.decimal));
    }

    public void equalsBTNPush(View view) {
        String userExp = display.getText().toString();
        String replaceStr = "";

        if (userExp.contains("sin-1")) {
            replaceStr = replaceFormula("sin-1", userExp, "arcsin");
        } else if (userExp.contains("cos-1")) {
            replaceStr = replaceFormula("cos-1", userExp, "arccos");
        } else if (userExp.contains("tan-1")) {
            replaceStr = replaceFormula("tan-1", userExp, "arctan");
        } else {
            replaceStr = userExp;
        }

        Expression exp = new Expression(replaceStr);
        userExp = userExp.replaceAll(getResources().getString(R.string.percent), "/100");
        String result = String.valueOf(Math.round(exp.calculate() * 1000.0) / 1000.0);

        String showCal = "";

        if (userExp.contains("sin-1")) {
            showCal = replaceFormula("sin-1", userExp, "sin<sup>-1</sup>");
        } else if (userExp.contains("cos-1")) {
            showCal = replaceFormula("cos-1", userExp, "cos<sup>-1</sup>");
        } else if (userExp.contains("tan-1")) {
            showCal = replaceFormula("tan-1", userExp, "tan<sup>-1</sup>");
        } else {
            showCal = userExp;
        }

        display.setText(result);
        previousCalculation.setText(Html.fromHtml(showCal));
    }

    public void backspaceBTNPush(View view) {
        int textLen = display.getText().length();
        String showText = display.getText().toString();
        if (showText.length() > 0)
            display.setText(showText.substring(0, textLen - 1));
    }

    public void sinBTNPush(View view) {
        updateText("sin(");
    }

    public void cosBTNPush(View view) {
        updateText("cos(");
    }

    public void tanBTNPush(View view) {
        updateText("tan(");
    }

    public void arcSinBTNPush(View view) {
        Button btn = findViewById(R.id.button24);
        String btnContent = btn.getText().toString();
        updateText(btnContent);
    }

    public void arcCosBTNPush(View view) {
        Button btn = findViewById(R.id.button26);
        String btnContent = btn.getText().toString();
        updateText(btnContent);
    }

    public void arcTanBTNPush(View view) {
        Button btn = findViewById(R.id.button25);
        String btnContent = btn.getText().toString();
        updateText(btnContent);
    }

    public void logBTNPush(View view) {
        updateText("log10(");
    }

    public void naturalLogBTNPush(View view) {
        updateText("ln(");
    }

    public void squaredBTNPush(View view) {
        updateText("√(");
    }

    public void eBTNPush(View view) {
        updateText("e");
    }

    public void piBTNPush(View view) {
        updateText("π");
    }

    public void absBTNPush(View view) {
        updateText("abs(");
    }

    public void primeBTNPush(View view) {
        updateText("ispr(");
    }

    public void xSquaredBTNPush(View view) {
        updateText("^(2)");

    }

    public void xPoweryBTNPush(View view) {
        updateText("^(");
    }

    public void precentBTNPush(View view) {
        updateText("%");
    }

}