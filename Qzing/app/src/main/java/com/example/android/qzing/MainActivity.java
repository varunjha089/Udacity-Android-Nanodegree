package com.example.android.qzing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int countPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeView(View view) {
        TextView askName = (TextView) findViewById(R.id.ask_name);
        EditText userName = (EditText) findViewById(R.id.username);
        Button startButton = (Button) findViewById(R.id.start_button);

        userName.setVisibility(View.GONE);
        startButton.setVisibility(View.GONE);

        String message = userName.getText().toString();
        message = "Hi " + message + "!";

        askName.setText(String.valueOf(message));
    }

    public void submit(View v) {
        int selectedID;
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.ques1);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.ques2);
        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.ques3);
        RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.ques6);
        RadioGroup radioGroup7 = (RadioGroup) findViewById(R.id.ques7);

        // Calculate score for Question 1
        selectedID = radioGroup1.getCheckedRadioButtonId();
        RadioButton radioButton1 = (RadioButton) findViewById(selectedID);
        if ((radioButton1.getText().toString()).equals("Snapchat")) {
            countPoints = countPoints + 1;
        }

        // Calculate score for Question 2
        selectedID = radioGroup2.getCheckedRadioButtonId();
        RadioButton radioButton2 = (RadioButton) findViewById(selectedID);
        if ((radioButton2.getText().toString()).equals("Siri")) {
            countPoints = countPoints + 1;
        }

        // Calculate score for Question 3
        selectedID = radioGroup3.getCheckedRadioButtonId();
        RadioButton radioButton3 = (RadioButton) findViewById(selectedID);
        if ((radioButton3.getText().toString()).equals("Russia")) {
            countPoints = countPoints + 1;
        }

        // Calculate score for Question 4
        EditText editText = (EditText) findViewById(R.id.ans_4);
        if ((editText.getText().toString().toLowerCase().trim()).equals("china")) {
            countPoints = countPoints + 1;
        }

        // Calculate score for Question 5
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.ans_5_1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.ans_5_2);
        if (checkBox1.isChecked() && checkBox2.isChecked()) {
            countPoints = countPoints + 1;
        }

        // Calculate score for Question 6
        selectedID = radioGroup6.getCheckedRadioButtonId();
        RadioButton radioButton6 = (RadioButton) findViewById(selectedID);
        if ((radioButton6.getText().toString()).equals("Tesla")) {
            countPoints = countPoints + 1;
        }
        // Calculate score for Question 7
        selectedID = radioGroup7.getCheckedRadioButtonId();
        RadioButton radioButton7 = (RadioButton) findViewById(selectedID);
        if ((radioButton7.getText().toString()).equals("Lumia")) {
            countPoints = countPoints + 1;
        }

        // Calculate and display the score
        Toast.makeText(this, "Thanks for playing! You scored "
                + countPoints + ".", Toast.LENGTH_SHORT).show();

        // Reset the score
        countPoints = 0;
    }
}
