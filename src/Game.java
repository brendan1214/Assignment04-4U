
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author farrb0382
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // whether or not the game is still running 
        boolean isFinished = false;

        // creating four random rows for the doctor and the three daleks
        int[] randRow = new int[4];
        // creating four random columns for the doctor and the three daleks 
        int[] randCol = new int[4];

        // for loop that gives the doctor and daleks the random rows and columns 
        for (int i = 0; i < randRow.length; i++) {
            // generating random coordinates for the rows and columns 
            int random = (int) (Math.random() * 12);
            int random2 = (int) (Math.random() * 12);
            // gives each position in the array a random value which determines where the four pegs will spawn on the board 
            randRow[i] = random;
            randCol[i] = random2;
        }

        // creating the board the game will be played on 
        Board b = new Board(12, 12);

        // the doctors random coordinates for row and column 
        Doctor doc = new Doctor(randRow[0], randCol[0]);

        // the daleks all spawn on random rows and columns 
        Dalek d1 = new Dalek(randRow[1], randCol[1]);
        Dalek d2 = new Dalek(randRow[2], randCol[2]);
        Dalek d3 = new Dalek(randRow[3], randCol[3]);

        // a message telling the user what to do 
        b.displayMessage("Click on the board to move the doctor (he is green).");

        // places the doctor on a random spot on the board 
        b.putPeg(Color.GREEN, doc.getRow(), doc.getCol());

        // places the three daleks randomly on the board 
        b.putPeg(Color.BLACK, d1.getRow(), d1.getCol());
        b.putPeg(Color.BLACK, d2.getRow(), d2.getCol());
        b.putPeg(Color.BLACK, d3.getRow(), d3.getCol());

        // while loop that runs as long as the game is still going on... doctor is still alive and daleks are chasing him
        while (!isFinished) {
            // the user clicks to move the doctor 
            Coordinate click = b.getClick();
            // removes the peg from the doctors original position 
            b.removePeg(doc.getRow(), doc.getCol());

            // removes the pegs from the daleks original positions  
            b.removePeg(d1.getRow(), d1.getCol());
            b.removePeg(d2.getRow(), d2.getCol());
            b.removePeg(d3.getRow(), d3.getCol());

            // gets the new row and column for the doctor 
            int row = click.getRow();
            int col = click.getCol();

            // it will output the new position of the doctor to update it 
            doc.move(row, col);

            // places the doctor in his new position 
            b.putPeg(Color.GREEN, doc.getRow(), doc.getCol());

            // calls upon the dalek class and gets the daleks to move once in the direction of the doctor 
            d1.advanceTowards(doc);
            d2.advanceTowards(doc);
            d3.advanceTowards(doc);

            // places three pegs in the new positions of the daleks 
            b.putPeg(Color.BLACK, d1.getRow(), d1.getCol());
            b.putPeg(Color.BLACK, d2.getRow(), d2.getCol());
            b.putPeg(Color.BLACK, d3.getRow(), d3.getCol());

            // if dalek #1 has collided with dalek #2 do the following 
            if (d1.hasCrashed(d2)) {
                d1.crash();
                d2.crash();
                // put down a red peg in the spot they collided to mark the crash 
                b.putPeg(Color.red, d1.getRow(), d1.getCol());
                b.putPeg(Color.red, d2.getRow(), d2.getCol());
            }

            // if dalek #1 has collided with dalek #3 do the following 
            if (d1.hasCrashed(d3)) {
                d1.crash();
                d3.crash();
                // put down a red peg in the spot they collided to mark the crash 
                b.putPeg(Color.red, d3.getRow(), d3.getCol());
                b.putPeg(Color.red, d1.getRow(), d1.getCol());
            }

            // if dalek #2 has collided with dalek #3 do the following 
            if (d2.hasCrashed(d3)) {
                d2.crash();
                d3.crash();
                // put down a red peg in the spot they collided to mark the crash 
                b.putPeg(Color.red, d2.getRow(), d2.getCol());
                b.putPeg(Color.red, d3.getRow(), d3.getCol());
            }

            // if all the daleks have crashed into one another do the following 
            if (d2.hasCrashed(d3) && d1.hasCrashed(d3) && d1.hasCrashed(d2)) {
                // output a message telling the user they have won 
                b.displayMessage("Congratulations You Win!");
                // stop the while loop and the game 
                isFinished = true;
            }

            // if the doctor has collided with any dalek do the following 
            if (doc.captureCheck(d1) || doc.captureCheck(d2) || doc.captureCheck(d3)) {
                // put down a yellow peg indicating where the doctor has crashed 
                b.putPeg(Color.yellow, doc.getRow(), doc.getCol());
                // output a message telling the user they have lost
                b.displayMessage("Game Over, You Lose!");
                // stop the while loop and the game 
                isFinished = true;
            }
        }
    }
}
