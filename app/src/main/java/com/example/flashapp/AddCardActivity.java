package com.example.flashapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        ImageView CancleButton = findViewById(R.id.my_cancle_Btn);
        ImageView SaveButton =findViewById(R.id.my_save_icon);
        EditText editQuestion = findViewById(R.id.editQuestionField);
        EditText editAnswer = findViewById(R.id.editAnswerField);
        String s1 = getIntent().getStringExtra("string1_key");
        editQuestion.setText(s1);



        CancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//on click listener for the save button
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question =((EditText)findViewById(R.id.editQuestionField)).getText().toString();
                String answer =((EditText)findViewById(R.id.editAnswerField)).getText().toString();

                Intent data =new Intent();     // create a new Intent, this is where we will put our data
                data.putExtra("string1", question);  // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", answer);   // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK,data);  // set result code and bundle data for response
                finish();    // closes this activity and pass data to the original activity that launched this activity
            }
        });
    }
}