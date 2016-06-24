package com.mercadeotodo.kidsalphabet;


import java.util.ArrayList;
import java.util.List;

public class Animals {

    protected final String mAnimalsName [] =
            {"nt ", "ear", "at",
                    "og", "lephant", "irefly",
                    "iraffe", "orse", "mpala",
                    "ellyfish ","oala","ion"
                    ,"onkey","ewt","ctopus","enguin"
                    ,"ueen bee ","acoon","quirrell"
                    ,"ortoise ","rial","iper","hale"
                    ,"rayfish","ak","ebra"};


    public final int mAnimalImg [][] = {
            {R.drawable.a, R.drawable.ant2},
            {R.drawable.b, R.drawable.bear},
            {R.drawable.c, R.drawable.cat},
            {R.drawable.d, R.drawable.dog},
            {R.drawable.e, R.drawable.elephant},
            {R.drawable.f, R.drawable.firefly},
            {R.drawable.g, R.drawable.giraffe},
            {R.drawable.h, R.drawable.horse},
            {R.drawable.i, R.drawable.impala},
            {R.drawable.j, R.drawable.jellyfish},
            {R.drawable.k, R.drawable.koala},
            {R.drawable.l, R.drawable.lion},
            {R.drawable.m, R.drawable.monkey},
            {R.drawable.n, R.drawable.newt},
            {R.drawable.o, R.drawable.octopus},
            {R.drawable.p, R.drawable.penguin},
            {R.drawable.q, R.drawable.queenbee},
            {R.drawable.r, R.drawable.racoon},
            {R.drawable.s, R.drawable.squirrell},
            {R.drawable.t, R.drawable.tortoise},
            {R.drawable.u, R.drawable.urial},
            {R.drawable.v, R.drawable.viper},
            {R.drawable.w, R.drawable.whale},
            {R.drawable.x, R.drawable.xrayfish},
            {R.drawable.y, R.drawable.yak},
            {R.drawable.z, R.drawable.zebra}
    };

    public final int animalsLookingToRight [] =
            {3, 5, 8, 11, 16, 17, 18, 23};

    public final int animalsLookingToLeft [] =
            {0, 1, 4, 6, 7, 12, 13, 20, 21, 22, 24};

    public final int animalsLookingToFront [] =
            {2, 9, 10, 14, 15, 19, 25};



    public int which;

    public Animals(int which){
        this.which = which;
    }

    public boolean animalsToRight (int animal){
        for (int i = 0; i < animalsLookingToRight.length; i++){
            if (animalsLookingToRight[i] == animal){
                return true;
            }
        }
        return false;
    }

    public boolean animalsToLeft (int animal){
        for (int i = 0; i < animalsLookingToLeft.length; i++){
            if (animalsLookingToLeft[i] == animal){
                return true;
            }
        }
        return false;
    }

    public boolean animalsToFront (int animal){
        for (int i = 0; i < animalsLookingToFront.length; i++){
            if (animalsLookingToFront[i] == animal){
                return true;
            }
        }
        return false;
    }



    public static List<Animals> createAnimalsList() {
        final List<Animals> memes = new ArrayList<>();
        memes.add(new Animals(0));
        memes.add(new Animals(1));
        memes.add(new Animals(2));
        memes.add(new Animals(3));
        memes.add(new Animals(4));
        memes.add(new Animals(5));
        memes.add(new Animals(6));
        memes.add(new Animals(7));
        memes.add(new Animals(8));
        memes.add(new Animals(9));
        memes.add(new Animals(10));
        memes.add(new Animals(11));
        memes.add(new Animals(12));
        memes.add(new Animals(13));
        memes.add(new Animals(14));
        memes.add(new Animals(15));
        memes.add(new Animals(16));
        memes.add(new Animals(17));
        memes.add(new Animals(18));
        memes.add(new Animals(19));
        memes.add(new Animals(20));
        memes.add(new Animals(21));
        memes.add(new Animals(22));
        memes.add(new Animals(23));
        memes.add(new Animals(24));
        memes.add(new Animals(25));
        return memes;
    }


    public int getmAnimalImg_Ani(int index) {
        return mAnimalImg[index][1];
    }

    public int getmAnimalImg_Let(int index) {
        return mAnimalImg[index][0];
    }



}
