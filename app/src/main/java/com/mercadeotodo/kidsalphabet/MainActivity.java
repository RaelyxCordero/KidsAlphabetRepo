package com.mercadeotodo.kidsalphabet;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageButton play_button;
    ImageView tittle;
    int v_x = 2, v_y = 2;
    public static int contadorIntersticial;
    protected float mScreenWth, mScreenHht, mFinalPosX, mFinalPosY, mLayoutWth, mLayoutHht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        tittle = (ImageView) findViewById(R.id.tittle);
        play_button = (ImageButton) findViewById(R.id.play);
        play_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });




    }


    @Override
    protected void onStart() {
        super.onStart();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mLayoutHht = tittle.getLayoutParams().height;
        mLayoutWth = tittle.getLayoutParams().width;

        mScreenWth = metrics.widthPixels;
        mScreenHht = metrics.heightPixels - mLayoutHht/2;

        mFinalPosX = (mScreenWth / 2) - (mLayoutWth / 2);
        mFinalPosY = 0;
    }


    private void log(String text) {
        Log.d("hey", text);
    }

    private void log2(float text) {
        Log.d("hey", Float.toString(text));
    }

    protected void onResume() {
        super.onResume();
        BackgroundMusic.play(this,R.raw.back_music);
        useAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.stop(this);
    }

    public void useAnimation() {


        final Handler handlerTres = new Handler();

        final Runnable rTres = new Runnable() {

            public void run() {
                    onMove(mFinalPosX, mFinalPosY);
                    handlerTres.postDelayed(this, 100);
            }

        };
        handlerTres.postDelayed(rTres, 0);


    }

    public void onMove(float iniwidth, float iniheight) {

        tittle.animate()
                .x(iniwidth)
                .y(iniheight)
                .setDuration(0)
                .start();

        if ((iniwidth + v_x + mLayoutWth > mScreenWth) || (iniwidth + v_x < 0)){
            v_x = -v_x;
        }
        if ((iniheight + v_y + mLayoutHht > mScreenHht) || (iniheight + v_y  < 0)){
            v_y = -v_y;
        }

        mFinalPosX = iniwidth + v_x;
        mFinalPosY = iniheight + v_y;

        tittle.animate()

                .x(mFinalPosX)
                .y(mFinalPosY)
                .setDuration(10)
                .start();



    }

}

