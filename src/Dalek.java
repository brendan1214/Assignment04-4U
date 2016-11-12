/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farrb0382
 */
public class Dalek {

    // variables for the game (column, row and a true or false stating whether or not the dalek has crashed)
    private int col;
    private int row;
    private boolean didCrash;

    /**
     * the dalek for the game
     *
     * @param row the row that the dalek is on
     * @param col the column the dalek is on
     */
    public Dalek(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public void advanceTowards(Doctor doc) {
        //stores the coordinates of the doctor's current location
        int row = doc.getRow();
        int col = doc.getCol();

        //formula used to calculate the distance between doctor's coordinates and dalek's coordinates 
        int rowMove = this.row - row;
        int colMove = this.col - col;

        //as long as the dalek has not crashed
        if (!didCrash) {
            //adds one to row's coordinate making dalek move towards doctor, that way it is now closer to 0 (tries to reach 0)
            if (rowMove <= 0) {
                this.row++;
            }

            //removes one to row's coordinate making dalek move towards doctor, that way it is now closer to 0 (tries to reach 0)
            if (rowMove >= 0) {
                this.row--;
            }

            //adds one to column's coordinate making dalek move towards doctor, that way it is now closer to 0 (tries to reach 0)
            if (colMove <= 0) {
                this.col++;
            }

            //removes one to column's coordinate making dalek move towards doctor, that way it is now closer to 0 (tries to reach 0)
            if (colMove >= 0) {
                this.col--;
            }
        }

    }

    public void crash() {
        this.didCrash = true;
    }

    /**
     * checks to see if there was a collision with the daleks 
     * @param d represents the daleks involved in the crash 
     * @return whether or not there was a crash 
     */
    public boolean hasCrashed(Dalek d) {

        //checks if coordinate of one dalek is the same as the other (row,col) 
        if (this.col == d.getCol() && this.row == d.getRow()) {
            return true;
        } else {
            return false;
        }
    }
}


