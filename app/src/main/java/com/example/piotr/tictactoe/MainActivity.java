package com.example.piotr.tictactoe;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String LOG = "TicTacToe";
    public static int height, width;
    TextView ticTacToeTextView, tapToPlayTextView;
    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticTacToeTextView = findViewById(R.id.tictactoe_textview);
        tapToPlayTextView = findViewById(R.id.tap_to_play_textview);
        relativeLayout = findViewById(R.id.relative_layout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        ticTacToeTextView.setY(height / 4);
        tapToPlayTextView.setY(height / 2);

        Animator alphaAnimator = ObjectAnimator.ofFloat(tapToPlayTextView, View.ALPHA, 0f, 1f);
        alphaAnimator.setDuration(1000);
        ((ObjectAnimator) alphaAnimator).setRepeatMode(ValueAnimator.REVERSE);
        ((ObjectAnimator) alphaAnimator).setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.start();

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Board.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}
