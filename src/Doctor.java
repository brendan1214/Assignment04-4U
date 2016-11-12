/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farrb0382
 */
public class Doctor {
    
    private int row;
    private int col;
    private boolean didCrash;
    
    /**
     * constructor for the doctor 
     * @param row the row the doctor is on
     * @param col the column the doctor is on 
     */
    public Doctor(int row, int col){
        this.col = col;
        this.row = row;
    }

    /**
     * the code that will move the doctor around the board 
     * @param row2 the new the doctor is moving to 
     * @param col2 the new column the doctor is moving to 
     */
    public void move(int row2, int col2) {
        
        // getting a random spot to spawn the doctor on 
        int randRow = (int) (Math.random() * 12);
        int randCol = (int) (Math.random() * 12);

        // finding a difference between the two coordinates to determine how the doctor will be moving 
        int row3 = this.row - row2;
        int col3 = this.col - col2;

        //if difference of the two coordinates is 1 in any direction of the doctor do the following 
        if (row3 <= 1 && row3 >= -1 && col3 <= 1 && col3 >= -1) {
            this.row = row2;    // the old row is now equal to the new one 
            this.col = col2;    // the old col is now equal to the new one 
            // if the difference of the two coordinates is more then one teleport the doctor to a random row and col
        } else {
            this.row = randRow;
            this.col = randCol;
        }
    }   
    
    /**
     * get the column the doctor is on
     * @return the column the doctor is on
     */
    public int getCol(){
        return this.col;
    }
    
    /**
     * get the row the doctor is on
     * @return the row the doctor is on
     */
    public int getRow(){
        return this.row;
    }

    /**
     * check to see if the doctor has collided with a dalek 
     * @param d a variable to represent a dalek 
     * @return whether or not the doctor has collided with a dalek 
     */
    public boolean captureCheck(Dalek a) {
        //checks doctor's coordinates with dalek's
        if (this.getCol() == a.getCol() && this.getRow() == a.getRow()) {
            return didCrash = true;
        } else {
            return didCrash = false;
        }
    }
}
