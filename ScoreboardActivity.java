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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    public List<Player> playerList = new ArrayList<>();
    TextView rollText, messageText;
    Button returnButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard_activity);
        setTitle("Scoreboard");

        setUIControls();

        Bundle bundle = getIntent().getExtras();

        String playerName = bundle.getString("playerName");
        int rolls = bundle.getInt("rolls");
        String json = bundle.getString("playerData");

        rollText.setText(String.format("%s Total Dice Rolls: ", rolls));
        messageText.setText(String.format("Thanks for playing, %s", playerName));

        Gson gson = new Gson();
        Type listOfPlayers = new TypeToken<List<Player>>() {}.getType();
        playerList = gson.fromJson(json, listOfPlayers);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);


        scoreAdapter adapter = new scoreAdapter(ScoreboardActivity.this, playerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        returnButton.setOnClickListener(v -> returnToMain());

    }

    public void setUIControls() {
        rollText = findViewById(R.id.rollText);
        messageText = findViewById(R.id.textMessage);
        returnButton = findViewById(R.id.returnButton);
    }

    public void returnToMain() {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

}
