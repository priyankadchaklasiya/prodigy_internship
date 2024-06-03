package com.example.ad_tic_tok_game_task4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isXTurn = true;
    private String[] board = new String[9];
    private Button[] buttons = new Button[9];
    private GridLayout gridLayout;
    private Button buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        buttonReset = findViewById(R.id.buttonReset);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (board[index] == null) {
                        board[index] = isXTurn ? "X" : "O";
                        buttons[index].setText(board[index]);
                        if (checkForWin()) {
                            Toast.makeText(MainActivity.this, board[index] + " wins!", Toast.LENGTH_SHORT).show();
                            disableButtons();
                        } else if (isBoardFull()) {
                            Toast.makeText(MainActivity.this, "It's a draw!", Toast.LENGTH_SHORT).show();
                        }
                        isXTurn = !isXTurn;
                    }
                }
            });
        }

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private boolean checkForWin() {
        String[][] winPositions = {
                {board[0], board[1], board[2]},
                {board[3], board[4], board[5]},
                {board[6], board[7], board[8]},
                {board[0], board[3], board[6]},
                {board[1], board[4], board[7]},
                {board[2], board[5], board[8]},
                {board[0], board[4], board[8]},
                {board[2], board[4], board[6]}
        };

        for (String[] positions : winPositions) {
            if (positions[0] != null && positions[0].equals(positions[1]) && positions[0].equals(positions[2])) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (String s : board) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void resetGame() {
        isXTurn = true;
        board = new String[9];
        for (Button button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }
}
