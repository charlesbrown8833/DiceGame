/**
 * Module 9 Assignment: This application is dice game that utilizes the Dice class that implements
 * Parcelable in order to pass the data into an object that is saved and restored once the
 * application is restored. This application also uses the ImageView to display an image of a die.
 * Adding a new Activity and using recyclerView to display data and using Gson.
 *
 * Class: CITC-2376-C01
 *
 * @author Charles Brown
 * @version April 10, 2024
 *
 */
package com.example.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView rollResult, totalText;
    private ImageView dieImage1, dieImage2, dieImage3;
    private EditText playerName;
    private Button rollButton, scoreButton;
    public Dice dice;
    public Player player;
    public String userName, previousPlayer;
    private List<Player> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Dice Game");

        setUIControls();
        setStartImages();
        startGame();

        rollButton.setOnClickListener(view -> {

            rollDiceBtnClicked();

            Log.i("die1", String.valueOf(dice.getDie1()));
            Log.i("die2", String.valueOf(dice.getDie2()));
            Log.i("die3", String.valueOf(dice.getDie3()));


        });

        scoreButton.setOnClickListener(v -> startScoreBoardActivity(player));
    }

    public void startScoreBoardActivity(Player player) {
        playerList.add(new Player(userName, dice.getTotalScore(), dice.getDoubles(), dice.getTriples(), dice.getRolls()));
        Gson gson = new Gson();
        String json = gson.toJson(playerList);
        Intent myIntent = new Intent(this, ScoreboardActivity.class);
        myIntent.putExtra("playerName", player.name);
        myIntent.putExtra("rolls", dice.getRolls());
        myIntent.putExtra("playerData", json);
        startActivity(myIntent);
    }

    public void setStartImages() {
        dieImage1.setImageResource(R.drawable.die_1);
        dieImage2.setImageResource(R.drawable.die_1);
        dieImage3.setImageResource(R.drawable.die_1);
    }

    public void setUIControls() {
        playerName = findViewById(R.id.playerName);
        rollResult = findViewById(R.id.rollResult);
        dieImage1 = findViewById(R.id.die1);
        dieImage2 = findViewById(R.id.die2);
        dieImage3 = findViewById(R.id.die3);
        totalText = findViewById(R.id.total);
        rollButton = findViewById(R.id.rollButton);
        scoreButton = findViewById(R.id.scoreButton);
    }

    public void rollDiceBtnClicked() {
        if (isValidPlayer()) {
            dice.rollDice();
            updateViews();
        }
    }

    protected boolean isValidPlayer() {
        userName = playerName.getText().toString();
        player.name = userName;
        if (userName.length() < 1) {
            showToast("Enter a name!");
            return false;
        }
        else if (!previousPlayer.equals(player.name)) {
            changePlayer();
        }
        return true;
    }

    public void changePlayer() {
        startGame();
        previousPlayer = userName;
        player = new Player(userName, 0, 0, 0, 0);
    }

    public void startGame() {
        dice = new Dice();
        player = new Player("", 0, 0, 0, 0);
        previousPlayer = "";
    }

    public void updateChecks(boolean doubleState, boolean tripleState) {
        dice.setDoubleCheck(doubleState);
        dice.setTripleCheck(tripleState);
    }

    protected void updateViews() {
        rollResult.setText(String.valueOf(dice.getCurrentScore()));
        totalText.setText(String.valueOf(dice.getTotalScore()));
        setImages();
    }

    //Save Dice data for application restore
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("parcelable", dice);
    }

    //Get saved Dice object instance
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.dice = savedInstanceState.getParcelable("parcelable");
    }

    //Set Images for the Dice
    public void setImages() {
        dice.setImage(dice.getDie1(), dieImage1);
        dice.setImage(dice.getDie2(), dieImage2);
        dice.setImage(dice.getDie3(), dieImage3);
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
        toast.show();
    }

    //Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true
    }

    //Get selected menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            DialogSettings settings = new DialogSettings();
            settings.show(getSupportFragmentManager(), "");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}