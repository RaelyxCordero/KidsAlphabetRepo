package com.mercadeotodo.kidsalphabet;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import tyrantgit.explosionfield.ExplosionField;


public class GameFragment extends Fragment {

    protected ImageView mivBalloon_cuerda;
    protected ImageView mivBalloon;
    protected ImageView mivAnimal;
    protected ImageView mivLetter;
    protected TextView animal_name;

    protected float mScreenWth, mScreenHht, mFinalPosX, mFinalPosY, mBalloonWth, mBalloonHht, mAnimalWth, mAnimalHht;
    protected boolean flag = false, click = false, leaved = false;
    protected long time;
    protected int i = 0;
    protected int tvPosition_x;
    protected int tvPosition_y;
    protected int caseOf = 0;
    protected final int VELOCITY = 10;
    protected int valueAnimal, valueBalloon;
    protected ViewGroup rootView;
    private Animals animal;
    protected ImageView back, next;
    protected AnimalSound animalSound;
    protected int posXBall, posYBall, posXAnimal, posYAnimal;


    protected final int mBalloonImg[] = {
            R.drawable.globo_amarillo,
            R.drawable.globo_anaranjado,
            R.drawable.globo_azul,
            R.drawable.globo_fucsia,
            R.drawable.globo_marron,
            R.drawable.globo_morado,
            R.drawable.globo_rojo,
            R.drawable.globo_verde,
            R.drawable.globo_verde_s,
    };

    protected final int mBalloon_sc[] = {
            R.drawable.globo_amarillo_sc,
            R.drawable.globo_anaranjado_sc,
            R.drawable.globo_azul_sc,
            R.drawable.globo_fucsia_sc,
            R.drawable.globo_marron_sc,
            R.drawable.globo_morado_sc,
            R.drawable.globo_rojo_sc,
            R.drawable.globo_verde_sc,
            R.drawable.globo_verde_s_sc,
    };

//    protected final int mExplosionImg[] = {
//            R.drawable.explotion1,
//            R.drawable.explotion2,
//            R.drawable.explotion3,
//            R.drawable.explotion4,
//            R.drawable.explotion4,
//            R.drawable.explotion5,
//            R.drawable.explotion6
//    };


    public GameFragment(){

    }



    public static GameFragment newInstance (int width, int height, Animals valueAnimal){

        GameFragment myFrag = new GameFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("mScreenWth",width);
        bundle.putInt("mScreenHht",height);
        bundle.putInt("valueAnimal", valueAnimal.which);
        myFrag.setArguments(bundle);
        myFrag.setRetainInstance(true);
        return myFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScreenWth = getArguments().getInt("mScreenWth");
        mScreenHht = getArguments().getInt("mScreenHht");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_game, container, false);

        valueAnimal = getArguments().getInt("valueAnimal");
        animal =  new Animals(valueAnimal);



        back = (ImageView) rootView.findViewById(R.id.back_button);
        next = (ImageView) rootView.findViewById(R.id.next_button);

        mivBalloon_cuerda = (ImageView) rootView.findViewById(R.id.balloon);
        mivBalloon = (ImageView) rootView.findViewById(R.id.balloon_sc);
        mivLetter = (ImageView) rootView.findViewById(R.id.letter);
        mivAnimal = (ImageView) rootView.findViewById(R.id.animal);
        animal_name = (TextView) rootView.findViewById(R.id.animal_name);


        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();

        animalSound = new AnimalSound(getContext(), valueAnimal);
        mivLetter.setImageResource(animal.getmAnimalImg_Let(valueAnimal));
        mivAnimal.setImageResource(animal.getmAnimalImg_Ani(valueAnimal));
        valueBalloon = random(0, (mBalloonImg.length - 1));
        mivBalloon_cuerda.setImageResource(mBalloonImg[valueBalloon]);



    }


    @Override
    public void onResume() {
        super.onResume();

        mBalloonHht = mivBalloon_cuerda.getLayoutParams().height;
        mBalloonWth = mivBalloon_cuerda.getLayoutParams().width;



        mAnimalHht = mivAnimal.getLayoutParams().height;
        mAnimalWth = mivAnimal.getLayoutParams().width;

        mFinalPosX = (mScreenWth / 2) - (mBalloonWth / 2);
        mFinalPosY = 0;

        mivBalloon_cuerda.setOnClickListener(genericClickListener);
        mivAnimal.setOnClickListener(genericClickListener);


        ViewPagerActivity.animationFinished = false;
        posXBall = (int)mFinalPosX;
        posYBall = (int)mFinalPosY;
        if (valueAnimal == 3 || valueAnimal == 6){
            posXAnimal = (int)mFinalPosX - 20;
        }else {
            posXAnimal = (int)mFinalPosX;
        }

        posYAnimal = (int)(mFinalPosY + mAnimalHht);
        leaving();

    }

    public void leaving(){

        final Handler handlerTres = new Handler();

        final Runnable rTres = new Runnable() {

            public void run() {
                if (!leaved) {
                    leaveTheAnimal(posXBall, posYBall, posXAnimal, posYAnimal);
                    handlerTres.postDelayed(this, time);
                }else {
                    animalLeaved();
                    log("****************AUN ESTOY EN EL HILO DE LEAVING*****************");
                }


            }

        };
        handlerTres.postDelayed(rTres, time);

    }

    public void leaveTheAnimal(int iniXBall, int iniYBall, int iniXAnimal, int iniYAnimal){
        leaved = false;
        mivBalloon_cuerda.animate()
                .x(iniXBall)
                .y(iniYBall)
                .setDuration(0)
                .start();


        mivBalloon.animate()
                .x(iniXBall)
                .y(iniYBall)
                .setDuration(0)
                .start();



        mivLetter.animate()
                .x(iniXBall)
                .y(iniYBall)
                .setDuration(0)
                .start();

        mivAnimal.animate()
                .x(iniXAnimal)
                .y(iniYAnimal)
                .setDuration(0)
                .start();

        posYAnimal = (int) (mScreenHht - (mAnimalHht + 20));
        posYBall = (int) (mScreenHht - (mBalloonHht + (mAnimalHht) / 2) );

        int dibujableEn = (int)(mScreenWth - mAnimalWth);

        if (animal.animalsToRight(valueAnimal)){
            int ran = random(0, dibujableEn/2);

            posXAnimal = ran;
            posXBall = ran;
        }

        if (animal.animalsToLeft(valueAnimal)){
            int ran = random(dibujableEn/2, dibujableEn);
            posXAnimal = ran;
            posXBall = ran;
        }

        if (animal.animalsToFront(valueAnimal)){
            int ran = random(0, dibujableEn);
            posXAnimal = ran;
            posXBall = ran;
        }

        setTimeAnimation(distance(iniXBall, iniYBall, iniXAnimal, iniYAnimal));


        mivBalloon_cuerda.animate()
                .x(posXBall)
                .y(posYBall)
                .setDuration(time)
                .start();

        mivBalloon.animate()
                .x(posXBall)
                .y(posYBall)
                .setDuration(time)
                .start();

        mivLetter.animate()
                .x(posXBall)
                .y(posYBall)
                .setDuration(time)
                .start();

        if (valueAnimal == 3 || valueAnimal == 6){
            mivAnimal.animate()
                    .x(posXAnimal - 20)
                    .y(posYAnimal - 20)
                    .setDuration(time)
                    .start();
        }else {
            mivAnimal.animate()
                    .x(posXAnimal)
                    .y(posYAnimal)
                    .setDuration(time)
                    .start();
        }


        leaved = true;
    }

    public void animalLeaved(){


        mivBalloon_cuerda.setVisibility(View.GONE);
        mivBalloon.setImageResource(mBalloon_sc[valueBalloon]);
        mBalloonHht = mivBalloon_cuerda.getLayoutParams().height;
        mBalloonWth = mivBalloon_cuerda.getLayoutParams().width;
        mFinalPosX = posXBall;
        mFinalPosY = posYBall;
        time = 0;
        mivBalloon.setOnClickListener(genericClickListener);
        balloonAloneAnimation();
    }



    public void balloonAloneAnimation() {


        final Handler handlerTres = new Handler();

        final Runnable rTres = new Runnable() {

            public void run() {
                if (!click) {
                    onMove(mFinalPosX, mFinalPosY);
                    handlerTres.postDelayed(this, time);
                }

            }

        };
        handlerTres.postDelayed(rTres, time);


    }

    private View.OnClickListener genericClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {



            if(v.getId() == R.id.letter){
                if (click) {
                    animalSound.playLetterSound();
                }
            }

            if(v.getId() == R.id.balloon){
                animalSound.playLetterSound();
            }


            if(v.getId() == R.id.balloon_sc){
                animalSound.playPlopSound();
                click = true;
                float touchedX = v.getX();
                float touchedY = v.getY();
                mivBalloon.animate().x(touchedX).y(touchedY).setDuration(0).start();


                onExplotion();
            }

            if(v.getId() == R.id.animal_name || v.getId() == R.id.animal){
                animalSound.playAnimalSound();
            }
        }
    };


    public void onExplotion() {

        ExplosionField explosionField = new ExplosionField(getContext());
        explosionField = explosionField.attach2Window(getActivity());
        mivBalloon.setVisibility(View.GONE);
        explosionField.explode(mivBalloon);
//        explosionField.explode(mivAnimal);

        letterName();
//                    mivBalloon.setVisibility(View.GONE);
        mivLetter.setOnClickListener(genericClickListener);
        animal_name.setOnClickListener(genericClickListener);

    }

//    public void onExplotion() {
//
//        final Handler handlerTres = new Handler();
//        final Runnable rTres = new Runnable() {
//
//            public void run() {
//
//                if (i < mExplosionImg.length) {
//                    mivBalloon.setImageResource(mExplosionImg[i]);
//                    i++;
//                    handlerTres.postDelayed(this, 20);
//                } else {
////                    ViewPagerActivity.contadorLetras();
//                    letterName();
////                    mivBalloon.setVisibility(View.GONE);
//                    mivLetter.setOnClickListener(genericClickListener);
//                    animal_name.setOnClickListener(genericClickListener);
//
//
//                    log("****************AUN ESTOY EN EL HILO DE ON EXPLOTION*****************");
//                }
//
//            }
//
//        };
//
//        handlerTres.postDelayed(rTres, 0);
//    }



    public void letterName(){

        caseOf = 0;

        int allNameWidth = mivLetter.getLayoutParams().width + animal_name.getLayoutParams().width;

        final float letterPositionWidth = 50 + (mScreenWth / 2) - (allNameWidth / 2);
        final float letterPositionHeight = mScreenHht / 4;

        int plus = 55;

//        if (valueAnimal == 12 || valueAnimal == 13 || valueAnimal == 14 ||
//                valueAnimal == 16 || valueAnimal == 22){
//            plus = 55;
//        }else plus = 70;

        tvPosition_x = (int) (letterPositionWidth + mivLetter.getLayoutParams().width) - plus;
        tvPosition_y = (int) letterPositionHeight + 60;


        final Handler handlerTres = new Handler();
        final Runnable rTres = new Runnable() {

            public void run() {


                if (caseOf == 0){
                    mivLetter.animate().rotationBy(360).x(letterPositionWidth).y(letterPositionHeight).setDuration(1000).start();
                    caseOf = 1;
                    handlerTres.postDelayed(this, 1000);
                }



                if (caseOf == 1){
                    animalSound.playLetterSound();
                    caseOf = 2;
                    handlerTres.postDelayed(this, 1000);
                }

                if (caseOf == 2){
                    animal_name.animate().x((tvPosition_x)).y(tvPosition_y).setDuration(1500);
                    animal_name.setText(animal.mAnimalsName[valueAnimal]);
                    caseOf = 3;
                    handlerTres.postDelayed(this, 2000);
                }

                if (caseOf == 3){
                    animalName();

                }


            }

        };

        handlerTres.postDelayed(rTres, 0);

    }

    public void animalName(){

        final Handler handlerTres = new Handler();
        final Runnable rTres = new Runnable() {

            public void run() {

                if (caseOf == 3){
                    animalSound.playAnimalSound();
                    caseOf = 4;
                    handlerTres.postDelayed(this, 1000);
                }else {
                    if(valueAnimal > 0){
                        back.setImageResource(R.drawable.back_button);
                    }
                    if (valueAnimal < 25){
                        next.setImageResource(R.drawable.next_button);
                    }

                    ViewPagerActivity.animationFinished = true;
                }

                log("****************AUN ESTOY EN EL HILO DE animalName*****************");
            }

        };

        handlerTres.postDelayed(rTres, 2000);

    }

    public void onMove(float iniwidth, float iniheight) {


        mivBalloon.animate()
                .x(iniwidth)
                .y(iniheight)
                .setDuration(0)
                .start();

        mivLetter.animate()
                .x(iniwidth)
                .y(iniheight)
                .setDuration(0)
                .start();


        mFinalPosX = getX();
        mFinalPosY = getY();

        setTimeAnimation(distance(iniwidth, iniheight, mFinalPosX, mFinalPosY));

        mivBalloon.animate()

                .x(mFinalPosX)
                .y(mFinalPosY)
                .setDuration(time)
                .start();
        mivLetter.animate()

                .x(mFinalPosX)
                .y(mFinalPosY)
                .setDuration(time)
                .start();

    }

    public void setTimeAnimation(float DISTANCE) {
        time = (long) (DISTANCE * VELOCITY);
    }

    private void log(String text) {
        Log.d("hey", text);
    }

    public float getX() {

        float newX;
        flag = false;
        newX = random(0, (mScreenWth + mBalloonWth));
        while (!flag) {

            if ((newX + mBalloonWth > mScreenWth) || (newX < 0)) {
                newX = random(0, (mScreenWth + mBalloonWth));
            } else {
                flag = true;
            }

        }

        return newX;
    }

    public float getY() {

        float newY;
        flag = false;
        newY = random(0, (mScreenHht - mBalloonHht));

        while (!flag) {

            if ((newY + mBalloonHht > mScreenHht) || (newY < 0)) {
                newY = random(0, (mScreenHht - mBalloonHht));
            } else {
                flag = true;
            }

        }

        return newY;
    }

    public float distance(float iniX, float iniY, float finX, float finY) {

        double x = finX - iniX;
        double ye = finY - iniY;

        x = Math.pow(x, 2.0);
        ye = Math.pow(ye, 2.0);

        return (float) Math.sqrt(x + ye);

    }

    public static int random(float min, float max) {

        Random r = new Random();
        int min1 = (int) min;
        int max1 = (int) max;
        return r.nextInt(max1 - min1 + 1) + min1;
    }



    @Override
    public void onStop() {
        super.onStop();


    }

    @Override
    public void onDetach() {
        super.onDetach();


    }
}
