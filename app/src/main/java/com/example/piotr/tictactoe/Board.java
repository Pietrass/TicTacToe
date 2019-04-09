package com.example.piotr.tictactoe;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import static com.example.piotr.tictactoe.MainActivity.height;

public class Board extends BaseActivity implements View.OnClickListener {

    TextSwitcher turnTextSwitcher;
    int turn, numOfMoves;
    GridLayout boardGrid;
    BoardButton[] boardButtonArray;
    public static String winner;
    RelativeLayout winnerRelative, boardRelative;
    TextView winnerTextView, playAgainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        turnTextSwitcher = findViewById(R.id.turn_textswitcher);
        turnTextSwitcher.setInAnimation(getBaseContext(), android.R.anim.slide_in_left);
        turnTextSwitcher.setOutAnimation(getBaseContext(), android.R.anim.slide_out_right);
        boardGrid = findViewById(R.id.board_grid);
        winnerRelative = findViewById(R.id.winner_relative_layout);
        winnerRelative.setY(height);
        winnerTextView = findViewById(R.id.winner_text_view);
        playAgainTextView = findViewById(R.id.play_again);
        playAgainTextView.setOnClickListener(this);
        boardRelative = findViewById(R.id.board_relative);

        turnTextSwitcher.setY(height / 7);
        turnTextSwitcher.setText("X turn");
        turn = 0;
        boardGrid.setBackgroundColor(Color.WHITE);

        boardButtonArray = new BoardButton[9];

        for (int i = 0; i < 9; i++) {
            String idString = "board_button_" + i;
            int id = getResources().getIdentifier(idString, "id", getPackageName());
            boardButtonArray[i] = findViewById(id);
            boardButtonArray[i].setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.play_again) {
            Intent intent = new Intent(getBaseContext(), Board.class);
            startActivity(intent);
        } else {
            BoardButton button = (BoardButton) v;
            setButtonBackground(button);
            v.setEnabled(false);
            numOfMoves++;
            checkIfAnybodyWon();
        }
    }

    private void checkIfAnybodyWon() {
        if ((boardButtonArray[0].buttonState.equals(boardButtonArray[1].buttonState) && boardButtonArray[1].buttonState.equals(boardButtonArray[2].buttonState)) && !(boardButtonArray[0].buttonState.equals("empty")) ||
                boardButtonArray[3].buttonState.equals(boardButtonArray[4].buttonState) && boardButtonArray[4].buttonState.equals(boardButtonArray[5].buttonState) && !(boardButtonArray[3].buttonState.equals("empty"))||
                boardButtonArray[6].buttonState.equals(boardButtonArray[7].buttonState) && boardButtonArray[7].buttonState.equals(boardButtonArray[8].buttonState) && !(boardButtonArray[6].buttonState.equals("empty"))||
                boardButtonArray[0].buttonState.equals(boardButtonArray[3].buttonState) && boardButtonArray[3].buttonState.equals(boardButtonArray[6].buttonState) && !(boardButtonArray[0].buttonState.equals("empty"))||
                boardButtonArray[1].buttonState.equals(boardButtonArray[4].buttonState) && boardButtonArray[4].buttonState.equals(boardButtonArray[7].buttonState) && !(boardButtonArray[1].buttonState.equals("empty"))||
                boardButtonArray[2].buttonState.equals(boardButtonArray[5].buttonState) && boardButtonArray[5].buttonState.equals(boardButtonArray[8].buttonState) && !(boardButtonArray[2].buttonState.equals("empty"))||
                boardButtonArray[0].buttonState.equals(boardButtonArray[4].buttonState) && boardButtonArray[4].buttonState.equals(boardButtonArray[8].buttonState) && !(boardButtonArray[0].buttonState.equals("empty"))||
                boardButtonArray[2].buttonState.equals(boardButtonArray[4].buttonState) && boardButtonArray[4].buttonState.equals(boardButtonArray[6].buttonState) && !(boardButtonArray[2].buttonState.equals("empty"))
                ) {
            if (turn == 0) {
                winner = "X";
                slideWinnerView(winnerRelative);
            } else {
                winner = "O";
                slideWinnerView(winnerRelative);
            }
        } else {
            if (turn == 0) {
                turnTextSwitcher.setText("O turn");
                turn = 1;
            } else {
                turnTextSwitcher.setText("X turn");
                turn = 0;
            }

            if (numOfMoves == 9) {
                winner = "Draw";
                slideWinnerView(winnerRelative);
            }
        }
    }

    public void slideWinnerView(RelativeLayout winnerView) {
        winnerView.setVisibility(View.VISIBLE);
        if (winner.equals("Draw")) {
            winnerTextView.setText(getResources().getString(R.string.draw));
        } else {
            winnerTextView.setText(winner + " " + getResources().getString(R.string.won));
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(winnerView, "translationY", height, height / 2.5f);
        animator.setDuration(300);
        animator.start();
    }

    private void setButtonBackground(BoardButton button) {
        if (turn == 0) {
            button.setText("X");
            button.setButtonState("X");
        } else {
            button.setText("O");
            button.setButtonState("O");
        }
    }
}
