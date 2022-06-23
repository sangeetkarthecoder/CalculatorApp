package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.enter_the_value).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }

    public void UpdateText(String strtoadd) {

        String oldstring = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstring.substring(0, cursorpos);
        String rightstr = oldstring.substring(cursorpos);

        if(getString(R.string.enter_the_value).equals(display.getText().toString())) {
            display.setText(strtoadd);
            display.setSelection(cursorpos+1);
        }
        else {
            display.setText(String.format("%s%s%s", leftstr, strtoadd, rightstr));
            display.setSelection(cursorpos+1);
        }
    }

    public void zerobtn(View view) {
        UpdateText("0");

    }

    public void onebtn(View view) {
        UpdateText("1");
    }

    public void twobtn(View view) {
        UpdateText("2");
    }

    public void threebtn(View view) {
        UpdateText("3");
    }

    public void fourbtn(View view) {
        UpdateText("4");
    }

    public void fivebtn(View view) {
        UpdateText("5");
    }

    public void sixbtn(View view) {
        UpdateText("6");
    }

    public void sevenbtn(View view) {
        UpdateText("7");
    }

    public void eightbtn(View view) {
        UpdateText("8");
    }

    public void ninebtn(View view) {
        UpdateText("9");
    }

    public void clearbtn(View view) {
        display.setText("");
    }

    public void parenthesisbtn(View view) {
        int cursorpos = display.getSelectionStart();
        int openpar = 0;
        int closedpar = 0;
        int textlen = display.getText().length();

        for(int i=0; i<cursorpos; i++) {
            if(display.getText().toString().substring(i, i+1).equals("(")) {
                openpar+=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")) {
                closedpar+=1;
            }
        }

        if(openpar==closedpar || display.getText().toString().substring(textlen-1, textlen).equals("(")) {
            UpdateText("(");
        }

        else if(closedpar<openpar && !display.getText().toString().substring(textlen-1, textlen).equals("(")) {
            UpdateText(")");
        }
        display.setSelection(cursorpos+1);
    }

    public void exponentbtn(View view) {
        UpdateText("^");
    }

    public void dividebtn(View view) {
        UpdateText("÷");
    }

    public void multiplytn(View view) {
        UpdateText("×");
    }

    public void minusbtn(View view) {
        UpdateText("-");
    }

    public void plusbtn(View view) {
        UpdateText("+");
    }

    public void plusminusbtn(View view) {
        UpdateText("%");
    }

    public void equalsbtn(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void dotbtn(View view) {
        UpdateText(".");
    }

    public void backspacebtn(View view) {
        int cursorpos = display.getSelectionStart();
        int Textlen = display.getText().length();

        if(cursorpos!=0 && Textlen!=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorpos-1, cursorpos, "");
            display.setText(selection);
            display.setSelection(cursorpos-1);
        }

    }

}