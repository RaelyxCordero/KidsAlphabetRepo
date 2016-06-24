package com.mercadeotodo.kidsalphabet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;


public class ViewPagerActivity extends FragmentActivity implements View.OnClickListener {
    public static ScreenSlidePagerAdapter screenSlidePagerAdapter;
    public static ViewPager pager;
    int mScreenWth, mScreenHht;
    static boolean animationFinished = false;
    Animals animals;
    List<Animals> animalsArrayList = Animals.createAnimalsList();


    public static InterstitialAd mInterstitialAd;
    public static AdView mAdView;
    public static AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice("ca-app-pub-3421367706673876/3773366945")
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mScreenWth = metrics.widthPixels;
        mScreenHht = (int) (metrics.heightPixels - metrics.heightPixels * 0.10);

        pager = (ViewPager) findViewById(R.id.pager);
        screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(screenSlidePagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                screenSlidePagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.back_button){

            if (animationFinished && pager.getCurrentItem() > 0){

                pager.setCurrentItem(pager.getCurrentItem() - 1);
                screenSlidePagerAdapter.notifyDataSetChanged();


            }

        }

        if(v.getId()==R.id.next_button){

            if (animationFinished && pager.getCurrentItem() < animalsArrayList.size() - 1){


                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                    screenSlidePagerAdapter.notifyDataSetChanged();


            }


        }

        if (v.getId() == R.id.redo){

                pager.setCurrentItem(pager.getCurrentItem());
                screenSlidePagerAdapter.notifyDataSetChanged();

        }
        if (v.getId() == R.id.home){
            super.onBackPressed();
            setContadorIntersticial();
        }

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return  GameFragment.newInstance(mScreenWth, mScreenHht, animalsArrayList.get(position));
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return animalsArrayList.size();
        }
    }

    public void setContadorIntersticial(){

        Log.e("antcontadorIntersticial", "" + MainActivity.contadorIntersticial);
        if (MainActivity.contadorIntersticial < 5){

            MainActivity.contadorIntersticial++;
        }else {

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-3421367706673876/5250100140");

            MainActivity.contadorIntersticial = 0;
            adRequest = new AdRequest.Builder()
                    .addTestDevice("ca-app-pub-3421367706673876/5250100140")
                    .build();

            mInterstitialAd.loadAd(adRequest);

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mInterstitialAd.show();
                    pager.setCurrentItem(pager.getCurrentItem());
                    screenSlidePagerAdapter.notifyDataSetChanged();

                }
            });
        }

        Log.e("descontadorIntersticial", "" + MainActivity.contadorIntersticial);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //BackgroundMusic.play(this, R.raw.back_music);

        mAdView = (AdView) findViewById(R.id.adView);


        AdRequest adRequestbanner = new AdRequest.Builder()
                .addTestDevice("ca-app-pub-3421367706673876/3773366945")
                .build();




        mAdView.loadAd(adRequestbanner);


    }



    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.play(this, R.raw.back_music);
        if(!animationFinished){
            pager.setCurrentItem(pager.getCurrentItem());
            screenSlidePagerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.stop(this);




    }


    @Override
    protected void onStop() {

        super.onStop();

    }

    private void log(String text) {
        Log.d("hey", text);
    }


}
