package com.example.piotr.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import static com.example.piotr.tictactoe.Board.winner;

public class DialogPlayAgain extends DialogFragment {

    String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (winner.equals("Draw")) {
            message = "Draw";
        } else {
            message = winner + " " + getString(R.string.won);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), Board.class);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}
