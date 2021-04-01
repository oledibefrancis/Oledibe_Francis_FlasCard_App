package com.example.flashapp;

import androidx.annotation.Nullable;
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

        //This statement is to get the data that MainActivity passed
        String s1 = getIntent().getStringExtra("string1_key");
        String s2 = getIntent().getStringExtra("string2_key");
        String s3 = getIntent().getStringExtra("string3_key");
        String s4 = getIntent().getStringExtra("string4_key");
        String s5 = getIntent().getStringExtra("string5_key");

        ((EditText)findViewById(R.id.editQuestionField)).setText(s1);
        ((EditText)findViewById(R.id.editAnswerField)).setText(s2);
        ((EditText)findViewById(R.id.editOption1)).setText(s3);
        ((EditText)findViewById(R.id.editOption2)).setText(s4);
        ((EditText)findViewById(R.id.editOption3)).setText(s5);



        CancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.right2_in, R.anim.left2_out);
            }

        });

//on click listener for the save button
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question =((EditText)findViewById(R.id.editQuestionField)).getText().toString();
                String answer =((EditText)findViewById(R.id.editAnswerField)).getText().toString();
                String option1 =((EditText)findViewById(R.id.editOption1)).getText().toString();
             //   String option2 =((EditText)findViewById(R.id.editOption2)).getText().toString();
             //   String option3 =((EditText)findViewById(R.id.editOption3)).getText().toString();

                Intent data =new Intent();     // create a new Intent, this is where we will put our data
                data.putExtra("string1", question);  // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", answer);   // puts another string into the Intent, with the key as 'string2
               data.putExtra("string3", option1);
             //   data.putExtra("string4", option2);
             //   data.putExtra("string5", option3);
                setResult(RESULT_OK,data);  // set result code and bundle data for response
                finish();    // closes this activity and pass data to the original activity that launched this activity
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        data.getStringExtra("string1_key");
////        data.getStringExtra("string2_key");
//        data.getStringExtra("string3_key");
//        data.getStringExtra("string4_key");
//        data.getStringExtra("string5_key");





//
//    }
}