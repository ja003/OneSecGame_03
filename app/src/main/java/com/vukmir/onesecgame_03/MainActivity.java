package com.vukmir.onesecgame_03;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnStart(View view){
        Intent game = new Intent(getApplicationContext(), LevelActivity.class);

        //lvl01.putExtra("score", score);

        startActivity(game);

    }
}
