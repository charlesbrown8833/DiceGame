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

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogSettings extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_settings, null);
        builder.setView(dialogView);

        final CheckBox doubleBox = dialogView.findViewById(R.id.doubleCheckBox);
        final CheckBox tripleBox = dialogView.findViewById(R.id.tripleCheckBox);
        final Button save = dialogView.findViewById(R.id.saveButton);
        final Button cancel = dialogView.findViewById(R.id.cancelButton);

        MainActivity calling = (MainActivity) getActivity();

        doubleBox.setChecked(calling.dice.getDoubleCheck());
        tripleBox.setChecked(calling.dice.getTripleCheck());
        save.setOnClickListener(view -> {
            calling.updateChecks(doubleBox.isChecked(), tripleBox.isChecked());
            dismiss();
        });

        cancel.setOnClickListener(view -> dismiss());

        return builder.create();
    }
}
