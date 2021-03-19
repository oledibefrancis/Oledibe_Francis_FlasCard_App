package com.example.flashapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards; //a variable holding the list of flashcards
    int currentCardDisplayedIndex = 0;//a variable to keep track of the index of the current card we're showing.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of variables

        TextView questionTextView = findViewById(R.id.flashcard_question);
        TextView answerTextView = findViewById(R.id.flashcard_answer);
        ImageView Addcard = findViewById(R.id.plus_Btn);
        ImageView OpenEye = findViewById(R.id.open_eye);
        ImageView CloseEye = findViewById(R.id.close_eye);
        ImageView EditButton =findViewById(R.id.edit_button);
        ImageView NextCard = findViewById(R.id.next_card);
        ImageView DeleteButton =findViewById(R.id.delete_button);
        TextView answer1 = findViewById(R.id.answer_1);
        TextView answer2 = findViewById(R.id.answer_2);
        TextView answer3 = findViewById(R.id.answer_3);


        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });
        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);

            }
        });



//The statement below sets an onclick listener for the answer options
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer2.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer3.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });

       //The statement below sets an onclick listener for the Open eye buttons
        OpenEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEye.setVisibility(View.INVISIBLE);
                CloseEye.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                answer1.setBackgroundColor(getResources().getColor(R.color.brown, null));
                answer2.setBackgroundColor(getResources().getColor(R.color.brown, null));
                answer3.setBackgroundColor(getResources().getColor(R.color.brown, null));
            }
        });


        //The statement below sets an onclick listener for the Closed eye buttons
        CloseEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEye.setVisibility(View.VISIBLE);
                CloseEye.setVisibility(View.INVISIBLE);
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
            }
        });

        //The statement belwo sets an on clikc listener for the edit button
        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                intent.putExtra("string1_key",((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("string2_key",((TextView) findViewById(R.id.flashcard_answer)).getText().toString());
                intent.putExtra("string3_key",((TextView) findViewById(R.id.answer_1)).getText().toString());
                intent.putExtra("string4_key",((TextView) findViewById(R.id.answer_2)).getText().toString());
                intent.putExtra("string1_key",((TextView) findViewById(R.id.answer_3)).getText().toString());
            }
        });


        //The statement below sets an on click listener for the plus button
        Addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });




        //This statement sets an onclick listner for the next card button
        NextCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0)
                    return;
                currentCardDisplayedIndex++;// advance our pointer index so we can show the next card

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Toast.makeText(getApplicationContext(), "You've reached the end of the cards, going back to start.", Toast.LENGTH_SHORT).show();
                    currentCardDisplayedIndex = 0;
                }
                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(flashcard.getAnswer());
            }
        });

        flashcardDatabase = new FlashcardDatabase(getApplicationContext()); //initializing the flashcard variable
        allFlashcards = flashcardDatabase.getAllCards();// variable initialization, we can access all flash cards

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer()); }


        //This statement sets an onClick listener for the delete button
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
            }
        });


        allFlashcards = flashcardDatabase.getAllCards();//This line updates the list of cards after a flashcard was deleted from the database




        }

        //End of OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 100 && resultCode==RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            TextView view = (TextView)findViewById(R.id.flashcard_question);
            view.setText(string1);
            TextView view2 = (TextView)findViewById(R.id.flashcard_answer);
            view2.setText(string2);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }



}