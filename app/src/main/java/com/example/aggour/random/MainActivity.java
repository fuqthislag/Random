package com.example.aggour.random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    EditText editText, editText2, editText3, editText4;
    TextView textView, textView2;
    CheckBox checkBox, checkBox2;
    Button button;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        button = (Button) findViewById(R.id.button);
        layout = (LinearLayout) findViewById(R.id.layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RNG RngObject = new RNG(
                        editText.getText().toString(),
                        editText2.getText().toString(),
                        checkBox.isChecked(),
                        MainActivity.this);
                RNG RngObject2 = new RNG(
                        editText3.getText().toString(),
                        editText4.getText().toString(),
                        checkBox.isChecked(),
                        MainActivity.this);

                textView.setText(RngObject.result);
                textView2.setText(RngObject2.result);
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    layout.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                } else {
                    layout.setVisibility(View.GONE);
                    textView2.setVisibility(View.GONE);
                }
            }
        });

    }
}