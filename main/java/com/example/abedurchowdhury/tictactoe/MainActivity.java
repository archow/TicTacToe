package com.example.abedurchowdhury.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //o = cross, 1 = nought
    int activePlayer = 0;
    boolean activeGame = true;

    //2 = unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winState = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
        String winningPlayer;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && activeGame) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.nought);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f)
                    .rotation(360)
                    .setDuration(300);

            //check to see if someone wins
            for (int[] winPosition : winState) {
                if (gameState[winPosition[0]]==gameState[winPosition[1]] &&
                        gameState[winPosition[1]]==gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2){

                    //Someone has won!

                    activeGame = false;
                    if (gameState[winPosition[0]]==0)
                        winningPlayer = "X";
                    else
                        winningPlayer = "O";

                    winnerMessage.setText(winningPlayer + " won!");

                    if (playAgainLayout.getVisibility()==View.INVISIBLE) {
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    boolean gameOver = true;
                    for (int gameCounter : gameState) {
                        if (gameCounter == 2)
                            gameOver = false;
                    }
                    if (gameOver) {
                        activeGame = false;

                        winnerMessage.setText("Neither player won!");

                        if (playAgainLayout.getVisibility()==View.INVISIBLE) {
                            playAgainLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        }
    }

    public void playAgain(View view) {
        LinearLayout playAgainLayout = (LinearLayout)findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        activeGame = true;
        //reinitiate gamestate
        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
