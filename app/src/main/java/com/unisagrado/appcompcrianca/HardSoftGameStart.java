package com.unisagrado.appcompcrianca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HardSoftGameStart extends AppCompatActivity {
    private TextView mScoreView, mQuestion;
    private ImageView mImageView;
    private Button mTrueButton, mFalseButton;

    private boolean mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private List<Question> mQuestionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_soft_game_start);

        mScoreView = findViewById(R.id.mScoreView);
        mImageView = findViewById(R.id.mImageView);
        mQuestion = findViewById(R.id.mQuestion);
        mTrueButton = findViewById(R.id.mTrueButton);
        mFalseButton = findViewById(R.id.mFalseButton);

        initializeQuestions();
        Collections.shuffle(mQuestionList);

        updateQuestion();

        // Logic for true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer) {
                    mScore++;
                    updateScore(mScore);
                    Toast.makeText(HardSoftGameStart.this, "Acertou!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HardSoftGameStart.this, "Errou!", Toast.LENGTH_SHORT).show();
                }

                if (mQuestionNumber >= mQuestionList.size()) {
                    Intent i = new Intent(HardSoftGameStart.this, HardSoftGameEnd.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("pontos", mScore);
                    i.putExtras(bundle);
                    HardSoftGameStart.this.finish();
                    startActivity(i);
                } else {
                    updateQuestion();
                }
            }
        });

        // Logic for false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mAnswer) {
                    mScore++;
                    updateScore(mScore);
                    Toast.makeText(HardSoftGameStart.this, "Acertou!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HardSoftGameStart.this, "Errou!", Toast.LENGTH_SHORT).show();
                }

                if (mQuestionNumber >= mQuestionList.size()) {
                    Intent i = new Intent(HardSoftGameStart.this, HardSoftGameEnd.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("pontos", mScore);
                    i.putExtras(bundle);
                    HardSoftGameStart.this.finish();
                    startActivity(i);
                } else {
                    updateQuestion();
                }
            }
        });
    }

    private void initializeQuestions() {
        mQuestionList.clear();
        for (int i = 0; i < QuizBook.questions.length; i++) {
            mQuestionList.add(new Question(QuizBook.images[i], QuizBook.questions[i], QuizBook.answers[i]));
        }
    }

    private void updateQuestion() {
        Question currentQuestion = mQuestionList.get(mQuestionNumber);
        mImageView.setImageResource(currentQuestion.getImageResId());
        mQuestion.setText(currentQuestion.getQuestionText());
        mAnswer = currentQuestion.isAnswer();
        mQuestionNumber++;
    }

    private void updateScore(int point) {
        mScoreView.setText(mScore + "/9");
    }

    // Inner class for handling questions
    private static class Question {
        private int imageResId;
        private String questionText;
        private boolean answer;

        public Question(int imageResId, String questionText, boolean answer) {
            this.imageResId = imageResId;
            this.questionText = questionText;
            this.answer = answer;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getQuestionText() {
            return questionText;
        }

        public boolean isAnswer() {
            return answer;
        }
    }
}
