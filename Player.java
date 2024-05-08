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

public class Player {
    String name;
    int score;
    int doubles;
    int triples;
    int rolls;


    public Player(String name, int score, int doubles, int triples, int rolls) {
        this.name = name;
        this.score = score;
        this.doubles = doubles;
        this.triples = triples;
        this.rolls = rolls;
    }
}
