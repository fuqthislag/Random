package com.example.aggour.random;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends Activity {

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
                new ThreadMe().execute(
                        editText.getText().toString() + "",
                        editText2,
                        checkBox.isChecked(),
                        textView);

                new ThreadMe().execute(
                        editText3.getText().toString() + "",
                        editText4,
                        checkBox.isChecked(),
                        textView2);
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

    public class ThreadMe extends AsyncTask<Object, Object, Void> {

        @Override
        protected void onProgressUpdate(Object... values) {
            String result = (String) values[0];
            String correct = (String) values[1];
            EditText editText2 = (EditText) values[2];
            TextView textView = (TextView) values[3];

            textView.setText(result);
            editText2.setText(correct);
        }

        @Override
        protected Void doInBackground(Object... params) {
            String editText = (String) params[0];
            EditText editText2 = (EditText) params[1];
            Boolean checkBox = (Boolean) params[2];
            TextView textView = (TextView) params[3];

            String txt2 = editText2.getText().toString() + "";

            int i, many, max;
            String result = "", correct;

            if (editText == "" || txt2 == "") {
                many = 1;
                max = 1;
            } else {
                many = Integer.parseInt(editText);
                max = Integer.parseInt(txt2);
                if (checkBox && max < many) {
                    max = many;
                }
            }

            correct = String.valueOf(max);
            publishProgress(result, correct, editText2, textView);


            if (checkBox) {
                ArrayList<Integer> list = new ArrayList<>();
                for (i = 1; i <= max; i++)
                    list.add(i);
                Collections.shuffle(list);
                for (i = 0; i < many; i++) {
                    result += list.get(i) + " ";
                    publishProgress(result, correct, editText2, textView);
                }
            } else {
                Random rand = new Random();
                for (i = 0; i < many; i++) {
                    result += 1 + rand.nextInt(max) + " ";
                    publishProgress(result, correct, editText2, textView);
                }
            }
            return null;
        }
    }
}