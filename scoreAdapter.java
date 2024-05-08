
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class scoreAdapter extends RecyclerView.Adapter<scoreAdapter.ListItemHolder> {

    private List<Player> mPlayerList;
    private ScoreboardActivity mScoreActivity;

    public scoreAdapter(ScoreboardActivity scoreActivity, List<Player> playerList) {
        mScoreActivity = scoreActivity;
        mPlayerList = playerList;
    }

    @NonNull
    @Override
    public scoreAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycle_view, parent, false);
        return new scoreAdapter.ListItemHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull scoreAdapter.ListItemHolder holder, int position) {
        Player player = mPlayerList.get(position);
        holder.playerName.setText(player.name);
        holder.playerScore.setText(String.valueOf(player.score));
        holder.playerRolls.setText("Doubles - " + String.valueOf(player.doubles) + "\tTriples - " + String.valueOf(player.triples));
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    public static class ListItemHolder extends RecyclerView.ViewHolder {

        TextView playerName, playerScore, playerRolls;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.playerName);
            playerScore = itemView.findViewById(R.id.playerScore);
            playerRolls = itemView.findViewById(R.id.playerRolls);

        }
    }
}
