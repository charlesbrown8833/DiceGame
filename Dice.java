/**
 * This application is dice game that utilizes the Dice class that implements
 * Parcelable in order to pass the data into an object that is saved and restored once the
 * application is restored. This application also uses the ImageView to display an image of a die.
 * Adding a new Activity and using recyclerView to display data and using Gson.
 *
 *
 * @author Charles Brown
 * @version April 10, 2024
 *
 */
package com.example.dicegame;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Dice implements Parcelable {
    private int die1, die2, die3, currentScore, totalScore, bonusPoints, rolls, doubles, triples;
    private boolean doubleScore, tripleScore;


    //Dice class constructor
    public Dice() {
        this.currentScore = 0;
        this.die1 = 0;
        this.die2 = 0;
        this.die3 = 0;
        this.totalScore = 0;
        this.bonusPoints = 0;
        this.rolls = 0;
        this.doubles = 0;
        this.triples = 0;
        this.doubleScore = true;
        this.tripleScore = true;
    }

    //Getters and Setters
    public int getCurrentScore() {return this.currentScore;}
    public int getDie1() {return this.die1;}
    public int getDie2() {return this.die2;}
    public int getDie3() {return this.die3;}
    public int getTotalScore() {return this.totalScore;}
    public int getRolls() {return this.rolls;}
    public int getDoubles() {return this.doubles;}
    public int getTriples() {return this.triples;}
    public boolean getDoubleCheck() {return doubleScore;}
    public boolean getTripleCheck() {return tripleScore;}
    public void setCurrentScore(int currentScore) {this.currentScore = currentScore;}
    public void setTotal(int totalScore) {this.totalScore = totalScore;}
    public void setDoubleCheck(boolean doubleBox) {doubleScore = doubleBox;}
    public void setTripleCheck(boolean tripleBox) {tripleScore = tripleBox;}

    //Method to return random number for die roll
    public void rollDice() {
        die1 = new Random().nextInt(6) + 1;
        die2 = new Random().nextInt(6) + 1;
        die3 = new Random().nextInt(6) + 1;
        currentScore = die1 + die2 + die3 + getBonusPoints();
        totalScore += currentScore;
        setCurrentScore(currentScore);
        setTotal(totalScore);
        rollStats();
    }

    public void rollStats() {
        if (die1 == die2 && die2 == die3) {
            rolls++;
            triples++;
        }
        else if (die1 == die2 || die2 == die3 || die1 == die3) {
            rolls++;
            doubles++;
        }
        else {
            rolls++;
        }
    }

    public int getBonusPoints() {
        bonusPoints = 0;

        if (isDoubleScore()) {
            bonusPoints = 50;
        }
        if (isTripleScore()) {
            bonusPoints = 100;
        }
        return bonusPoints;
    }

    public boolean isDoubleScore() {
        if (doubleScore) {
            return die1 == die2 || die2 == die3 || die1 == die3;
        }
        else {
            return false;
        }
    }

    public boolean isTripleScore() {
        if (tripleScore) {
            return die1 == die2 && die2 == die3;
        }
        else {
            return false;
        }
    }

    public void setImage(int result, ImageView dieImage) {
        if (result == 1) {
            dieImage.setImageResource(R.drawable.die_1);
        }
        else if (result == 2) {
            dieImage.setImageResource(R.drawable.die_2);
        }
        else if (result == 3) {
            dieImage.setImageResource(R.drawable.die_3);
        }
        else if (result == 4) {
            dieImage.setImageResource(R.drawable.die_4);
        }
        else if (result == 5) {
            dieImage.setImageResource(R.drawable.die_5);
        }
        else {
            dieImage.setImageResource(R.drawable.die_6);
        }
    }

    //Read data from parcel
    protected Dice(Parcel in) {
        die1 =  in.readInt();
        die2 = in.readInt();
        die3 = in.readInt();
        currentScore = in.readInt();
        totalScore = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Write data to parcel
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(die1);
        dest.writeInt(die2);
        dest.writeInt(die3);
        dest.writeInt(currentScore);
        dest.writeInt(totalScore);
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };
}
