package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_start,opt0,opt1,opt2,opt3;
    TextView tv_timer,tv_score,tv_questions,tv_bottomMessage;
    ProgressBar prog_timer;

    Game g=new Game();

    int secondsRemaining=30;

    CountDownTimer timer=new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining)+"sec");
            prog_timer.setProgress(30-secondsRemaining);

        }

        @Override
        public void onFinish() {

            opt0.setEnabled(false);
            opt1.setEnabled(false);
            opt2.setEnabled(false);
            opt3.setEnabled(false);
            tv_bottomMessage.setText("Time is up! "+g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));

            final Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);

                }
            },3000);


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        opt0 = findViewById(R.id.opt0);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);

        tv_timer = findViewById(R.id.tv_timer);
        tv_score = findViewById(R.id.tv_score);
        tv_questions = findViewById(R.id.tv_questions);
        tv_bottomMessage = findViewById(R.id.tv_bottomMessage);

        prog_timer = findViewById(R.id.prog_timer);

        tv_timer.setText("0sec");
        tv_questions.setText("");
        tv_bottomMessage.setText("Press Go");
        tv_score.setText("0");
        prog_timer.setProgress(0);

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                secondsRemaining=30;
                g=new Game();
                nextTurn();
                timer.start();

            }
        };

        View.OnClickListener answerButtonClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked=(Button)v;
                int answerSelected=Integer.parseInt(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextTurn();

            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        opt0.setOnClickListener(answerButtonClickListener);
        opt1.setOnClickListener(answerButtonClickListener);
        opt2.setOnClickListener(answerButtonClickListener);
        opt3.setOnClickListener(answerButtonClickListener);




    }
    private void nextTurn()
    {
        //create a new question
        //set text on answer buttons
        //enable answer buttons
        //start the timer

        g.makeNewQuestion();
        int [] answer=g.getCurrentQuestion().getAnswerArray();
        opt0.setText(Integer.toString(answer[0]));
        opt1.setText(Integer.toString(answer[1]));
        opt2.setText(Integer.toString(answer[2]));
        opt3.setText(Integer.toString(answer[3]));

        opt0.setEnabled(true);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_bottomMessage.setText(g.getNumberCorrect()+"/"+(g.getTotalQuestions()-1));


    }
}
