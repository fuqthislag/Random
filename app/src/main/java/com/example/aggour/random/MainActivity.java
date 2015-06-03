package com.example.aggour.random;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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
                new AsyncTask<Object, String, Void>() {
                    @Override
                    protected void onProgressUpdate(String... values) {
                        textView.setText(values[0]);
                        textView2.setText(values[1]);
                        editText2.setText(values[2]);
                        editText4.setText(values[3]);
                    }

                    @Override
                    protected Void doInBackground(Object... params) {
                        String editText = (String) params[0];
                        String editText2 = (String) params[1];
                        String editText3 = (String) params[2];
                        String editText4 = (String) params[3];
                        Boolean checkBox = (Boolean) params[4];

                        int i, many, max, many2, max2;
                        String result = "", result2 = "", correct = "", correct2 = "";

                        if (editText == "" || editText2 == "") {
                            many = 1;
                            max = 1;
                        } else {
                            many = Integer.parseInt(editText);
                            max = Integer.parseInt(editText2);
                            if (checkBox && max < many) {
                                max = many;
                                correct = String.valueOf(max);
                                publishProgress(result, result2, correct, correct2);
                            }
                        }

                        if (editText3 == "" || editText4 == "") {
                            many2 = 1;
                            max2 = 1;
                        } else {
                            many2 = Integer.parseInt(editText3);
                            max2 = Integer.parseInt(editText4);
                            if (checkBox && max2 < many2) {
                                max2 = many2;
                                correct2 = String.valueOf(max2);
                                publishProgress(result, result2, correct, correct2);
                            }
                        }

                        correct = String.valueOf(max);
                        correct2 = String.valueOf(max2);

                        try {
                            if (checkBox) {
                                ArrayList<Integer> list = new ArrayList<>();
                                for (i = 1; i <= max; i++)
                                    list.add(i);
                                Collections.shuffle(list);
                                for (i = 0; i < many; i++) {
                                    result += list.get(i) + " ";
                                    Thread.sleep(17);
                                    publishProgress(result, result2, correct, correct2);
                                }
                                ArrayList<Integer> list2 = new ArrayList<>();
                                for (i = 1; i <= max2; i++)
                                    list2.add(i);
                                Collections.shuffle(list2);
                                for (i = 0; i < many2; i++) {
                                    result2 += list2.get(i) + " ";
                                    Thread.sleep(17);
                                    publishProgress(result, result2, correct, correct2);
                                }
                            } else {
                                Random rand = new Random();
                                for (i = 0; i < many; i++) {
                                    result += 1 + rand.nextInt(max) + " ";
                                    Thread.sleep(17);
                                    publishProgress(result, result2, correct, correct2);
                                }
                                for (i = 0; i < many2; i++) {
                                    result2 += 1 + rand.nextInt(max2) + " ";
                                    Thread.sleep(17);
                                    publishProgress(result, result2, correct, correct2);
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(
                        editText.getText().toString() + "",
                        editText2.getText().toString() + "",
                        editText3.getText().toString() + "",
                        editText4.getText().toString() + "",
                        checkBox.isChecked());
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