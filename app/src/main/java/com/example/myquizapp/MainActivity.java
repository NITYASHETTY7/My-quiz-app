package com.example.myquizapp;

import android.app.AlertDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalQuestionTextview;
    Button ansA,ansB,ansC,ansD;
    Button  btn_submit;

    int score=0;
    int totalQuestion = questionanswer.question.length;
    int currentQuestionIndex=0;
    String selectedanswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         totalQuestionTextview=findViewById(R.id.total_questions);
         questionTextView = findViewById(R.id.questions);
         ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);
        btn_submit=findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalQuestionTextview.setText("Total Question: "+totalQuestion);
        loadNewQuestion();
    }

    private void loadNewQuestion(){
            if(currentQuestionIndex==totalQuestion) {
                finishQuiz();
                return;
            }
            questionTextView.setText(questionanswer.question[currentQuestionIndex]);
            ansA.setText(questionanswer.choices[currentQuestionIndex][0]);
            ansB.setText(questionanswer.choices[currentQuestionIndex][1]);
            ansC.setText(questionanswer.choices[currentQuestionIndex][2]);
            ansD.setText(questionanswer.choices[currentQuestionIndex][3]);

            selectedanswer="";

    }
    private void finishQuiz() {
        String passStatus;
        if (score >= totalQuestion * 0.6) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("your score is" + score + " Out of " + totalQuestion)
                .setPositiveButton("Restart", ((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton =(Button)view;
        if(clickedButton.getId()== R.id.btn_submit){
            if(!selectedanswer.isEmpty()){
                if(selectedanswer.equals(questionanswer.coorectans[currentQuestionIndex])){
                   score++;
                }else{
                    clickedButton.setBackgroundColor(Color.RED);
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }else{

            }
        }else{
            selectedanswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GRAY);
        }
    }
}