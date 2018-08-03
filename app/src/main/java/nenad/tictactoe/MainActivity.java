package nenad.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int matrix [][];
    boolean playerTurn;
    boolean gameOn;
    int moves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerTurn = true;
        matrix = new int[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                matrix[i][j] = 0;

        gameOn = true;
        moves = 0;
    }

    public void click(View view) {
        if(!gameOn)
            return;
        moves++;
        ImageView imageView = findViewById(view.getId());
        String [] coordinates = view.getTag().toString().split("\\s+");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if(matrix[x][y] > 0)
            return;

        if (playerTurn) {
            matrix[x][y] = 1;
            imageView.setImageResource(R.drawable.x);
        } else {
            matrix[x][y] = 2;
            imageView.setImageResource(R.drawable.o);
        }
        playerTurn = !playerTurn;

        if(chechForWin(1) == 1)
            endGame(1);
        else if(chechForWin(2) == 2)
            endGame(2);
        else if(moves == 9)
            endGame(0);
    }

    private void endGame(int i) {
        String winner = null;
        if(i == 0)
            winner = "Game is drawn";
        else if(i == 1)
            winner = "X wins";
        else
            winner = "O wins";

        gameOn = false;
        TextView view = findViewById(R.id.tvWinner);
        view.setText(winner);
    }

    public int chechForWin(int number){
        int k = 0;

        //checking for winner by rows
        //if someone won return number else return 0
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(matrix[i][j] == number)
                    k++;
            }
            if(k == 3)
                return number;
            k = 0;
        }

        //checking for winner by columns
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(matrix[j][i] == number)
                    k++;
            }
            if(k == 3)
                return number;
            k = 0;
        }

        //checking for winner by main diagonal
        for(int i = 0, j = 0; i < 3; i++, j++){
            if(matrix[i][j] == number)
                k++;
        }
        if(k == 3)
            return number;
        k = 0;

        //checking for winner by secondary diagonal
        for(int i = 2, j = 0; i >= 0; i--, j++){
            if (matrix[i][j] == number)
                k++;
        }
        if(k == 3)
            return number;

        return 0;
    }

}
