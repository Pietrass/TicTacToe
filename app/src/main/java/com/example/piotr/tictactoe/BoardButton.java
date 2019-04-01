package com.example.piotr.tictactoe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class BoardButton extends android.support.v7.widget.AppCompatButton {

    String buttonState = "empty";

    public BoardButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setButtonState(String buttonState) {
        this.buttonState = buttonState;
    }
}
