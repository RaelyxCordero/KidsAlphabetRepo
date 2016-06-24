package com.mercadeotodo.kidsalphabet;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

public class AnimalSound {

    SoundPool soundPool;

    int plopIdSound = R.raw.plop;

    protected final int mAnimalIdSound [] =
            {R.raw.ant, R.raw.bear , R.raw.cat,
                    R.raw.dog, R.raw.elephant, R.raw.firefly,
                    R.raw.giraffe,R.raw.horse, R.raw.impala,
                    R.raw.jellyfish, R.raw.koala, R.raw.lion,
                    R.raw.monkey, R.raw.newt, R.raw.octopus,
                    R.raw.penguin, R.raw.queenbee, R.raw.racoon,
                    R.raw.squirrel, R.raw.tortoise, R.raw.urial,
                    R.raw.viper, R.raw.whale, R.raw.xrayfish,
                    R.raw.yak, R.raw.zebra};

    protected final int mLetterIdSound [] =
            {R.raw.a, R.raw.b , R.raw.c,
                    R.raw.d, R.raw.e, R.raw.f,
                    R.raw.g, R.raw.h, R.raw.i,
                    R.raw.j, R.raw.k, R.raw.l,
                    R.raw.m, R.raw.n, R.raw.o,
                    R.raw.p, R.raw.q, R.raw.r,
                    R.raw.s, R.raw.t, R.raw.u,
                    R.raw.v, R.raw.w, R.raw.x,
                    R.raw.y, R.raw.z};

    int letterSound;
    int animalSound;
    int plopSound;
    private MediaPlayer mediaPlayer;

    private static boolean flag = true;

    public AnimalSound(Context context, int actual){
/*soundPool.play(
* id de pista
* volumen izq
* volumen der
* prioridad
* repeticiones
* ratio
*
* */
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        letterSound = soundPool.load(context, mLetterIdSound[actual], 0);
        animalSound = soundPool.load(context, mAnimalIdSound[actual], 0);
        plopSound = soundPool.load(context, plopIdSound, 0);
    }

    public void playAnimalSound (){
        soundPool.play(animalSound, 1, 1, 1, 0, 1);
    }

    public void playLetterSound (){
        soundPool.play(letterSound, 1, 1, 1, 0, 1);
    }

    public void playPlopSound (){
        soundPool.play(plopSound, 1, 1, 1, 0, 1);
    }



//    private static MediaPlayer player;
//    private static boolean flag = true;
//
//    public static void playAnimalSound (Context context, int id){
//        if (!flag){
//            return;
//        }
//        player = MediaPlayer.create(context,id);
//        player.setLooping(false);
//        player.start();
//        flag = false;
//        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                flag = true;
//            }
//        });
//    }
//
//    public static void playLetterSound (Context context, int id){
//        if (!flag){
//            return;
//        }
//        player = MediaPlayer.create(context,id);
//        player.setLooping(false);
//        player.start();
//        flag = false;
//        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                flag = true;
//            }
//        });
//    }
//
//    public static void playPlopSound (Context context){
//        player = MediaPlayer.create(context,R.raw.plop);
//        player.setLooping(false);
//        player.start();
//    }
//
//
//
//    public static void stop (Context context){
//        if (player != null){
//            player.stop();
//            player.release();
//            player = null;
//        }
//    }

}