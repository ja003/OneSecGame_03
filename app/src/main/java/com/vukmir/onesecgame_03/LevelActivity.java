package com.vukmir.onesecgame_03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import android.os.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Vukmir on 8.2.2015.
 */
public class LevelActivity extends Activity {

    public static int level;
    public static int score;
    public static int answer;
    public static int questNum;
    public final int WIN_SCORE = 5;
    public final int LOSE_SCORE = -1;

    public static TextView tvQuestion;
    public static TextView tvScore;
    public static TextView tvLevel;
    public static String[][] questionsField;
    public static int[][] correctAnswersField;
    public static RelativeLayout[][] viewableOptionsField;
    public static RelativeLayout clickableOptions;
    public static RelativeLayout topPanel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        LevelActivity game = new LevelActivity();

        game.tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        game.tvScore = (TextView) findViewById(R.id.tvScore);
        game.tvLevel = (TextView) findViewById(R.id.tvLevel);
        game.clickableOptions = (RelativeLayout) findViewById(R.id.clickableOptions);
        game.topPanel = (RelativeLayout) findViewById(R.id.topPanel);
        game.viewableOptionsField = new RelativeLayout[5][6];
        game.viewableOptionsField[1][1] = (RelativeLayout) findViewById(R.id.viewableOpt_01_01);
        game.viewableOptionsField[1][2] = (RelativeLayout) findViewById(R.id.viewableOpt_01_02);
        game.viewableOptionsField[1][3] = (RelativeLayout) findViewById(R.id.viewableOpt_01_03);
        game.viewableOptionsField[1][4] = (RelativeLayout) findViewById(R.id.viewableOpt_01_04);
        game.viewableOptionsField[1][5] = (RelativeLayout) findViewById(R.id.viewableOpt_01_05);

        game.startGame();



    }


    public void startGame(){
        level = 1;
        score = 0;
        answer = 1;
        questNum = 1;

        //show top panel
        topPanel.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.VISIBLE);

        //set questions [level] [questNum]
        questionsField = new String[5][6];
        questionsField[1][1] = "quest 1-1";
        questionsField[1][2] = "quest 1-2";
        questionsField[1][3] = "quest 1-3";
        questionsField[1][4] = "quest 1-4";
        questionsField[1][5] = "quest 1-5";

        //set correct answers [level] [questNum]
        correctAnswersField = new int[5][6];
        correctAnswersField[1][1] = 1;
        correctAnswersField[1][2] = 2;
        correctAnswersField[1][3] = 3;
        correctAnswersField[1][4] = 4;
        correctAnswersField[1][5] = 4;



        //tvScore.setText(""+score);

       generateQuestion();
    }

    public void generateQuestion(){

        checkScore();

        Random random = new Random();
        final int questNum = random.nextInt(4)+1;
        this.questNum = questNum;
        //System.out.println(questNum + this.questNum);
        tvLevel.setText(""+level);
        tvScore.setText(""+score);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvQuestion.setVisibility(View.VISIBLE);
                tvQuestion.setText(""+getQuestion(level,questNum));
            }
        },1000);



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickableOptions.setVisibility(View.VISIBLE);
                viewableOptionsField[level][questNum].setVisibility(View.VISIBLE);
            }
        },2000);


    }

    public void checkScore(){
        if(score > WIN_SCORE){
            level++;
        }
        else if(score < LOSE_SCORE){
            level--;
        }
    }

    public String getQuestion(int level, int questNum){
        String quest = questionsField[level][questNum];
        return quest;
    }

    public int getCorrectAnswer(int level, int questNum){
        int answer = correctAnswersField[level][questNum];
        return answer;
    }

    public void onClickBtn1(View view){
        answer = 1;
        checkAnswer();
        generateQuestion();
    }

    public void onClickBtn2(View view){
        answer = 2;
        checkAnswer();
        generateQuestion();
    }

    public void onClickBtn3(View view){
        answer = 3;
        checkAnswer();
        generateQuestion();
    }

    public void onClickBtn4(View view){
        answer = 4;
        checkAnswer();
        generateQuestion();
    }

    public void checkAnswer(){
        if(answer == getCorrectAnswer(level,questNum)){
            score++;
            clickableOptions.setVisibility(View.GONE);
            viewableOptionsField[level][questNum].setVisibility(View.GONE);
            tvQuestion.setVisibility(View.INVISIBLE);
        }
        else {
            score--;
            clickableOptions.setVisibility(View.GONE);
            viewableOptionsField[level][questNum].setVisibility(View.GONE);
            tvQuestion.setVisibility(View.INVISIBLE);
        }
    }











}

