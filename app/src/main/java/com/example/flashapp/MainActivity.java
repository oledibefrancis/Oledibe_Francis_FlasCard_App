package com.example.flashapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.plattysoft.leonids.ParticleSystem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards; //a variable holding the list of flashcards
    int currentCardDisplayedIndex = 0;//a variable to keep track of the index of the current card we're showing.
    CountDownTimer countDownTimer;

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

                View answerSideView = findViewById(R.id.flashcard_answer);
                View questionSideView = findViewById(R.id.flashcard_answer);


                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();

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
                answer1.setBackgroundColor(getResources().getColor(R.color.ligth_green, null));
                new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                        .setSpeedRange(0.2f, 0.6f)
                        .oneShot(findViewById(R.id.answer_1), 100);
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



//        // The block of code below is to implement a coundown timer
//        countDownTimer = new CountDownTimer(16000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//            }
//        };
////
//        private void startTimer() {
//            countDownTimer.cancel();
//            countDownTimer.start();
//        }

       //The statement below sets an onclick listener for the Open eye buttons
        OpenEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEye.setVisibility(View.INVISIBLE);
                CloseEye.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                answer1.setBackgroundColor(getResources().getColor(R.color.green, null));
                answer2.setBackgroundColor(getResources().getColor(R.color.green, null));
                answer3.setBackgroundColor(getResources().getColor(R.color.green, null));
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
                intent.putExtra("string1_key",((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("string2_key",((TextView) findViewById(R.id.flashcard_answer)).getText().toString());
                intent.putExtra("string3_key",((TextView) findViewById(R.id.answer_1)).getText().toString());
                intent.putExtra("string4_key",((TextView) findViewById(R.id.answer_2)).getText().toString());
                intent.putExtra("string5_key",((TextView) findViewById(R.id.answer_3)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        //The statement below sets an on click listener for the plus button
        Addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
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

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
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
                ((TextView)findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex-1).getQuestion());
                ((TextView)findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex-1).getQuestion());
                allFlashcards = flashcardDatabase.getAllCards();
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