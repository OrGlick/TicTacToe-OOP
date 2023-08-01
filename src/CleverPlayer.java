public class CleverPlayer implements Player{
    private WhateverPlayer whateverPlayer = new WhateverPlayer();
    private int[] locationToWinOrInterrupte = new int[2];
    private Mark oppositeMark;

    //if there is 1 move to win - the player wins.
    //else - I used Decorator design pattern.
    //the clever player uses the whatever player to put his mark on a random place
    // TODO: 01/08/2023 Make checks of winOrInterrupt of boards bigger the 3*3.
    //  Now it isn't checking all horizontals/verticals/diagonals.
    //  need check after the last mark-put of the enemy.
    //  This is the elegant solution. Now it is manual checks

    @Override
    public void playTurn(Board board, Mark mark) {
        if(mark == Mark.X)
            oppositeMark = Mark.O;
        else
            oppositeMark = Mark.X;
        //check if clever can win or need to interrupt enemy streak
        if(shouldWinOrInterruptStreak(board, mark) || shouldWinOrInterruptStreak(board, oppositeMark)){
            board.putMark(mark, locationToWinOrInterrupte[0], locationToWinOrInterrupte[1]);
        }
        else {
            whateverPlayer.playTurn(board, mark);
        }
    }
    //check for a 1 move to win place
    private boolean shouldWinOrInterruptStreak(Board board, Mark mark) {
        int[] results;
        //check from 0,0 to right, down and right-down
        //check for a horizontal to the right streak
        results = countMarksOnAGivenDirection(board, 0, 0, mark, 0, 1);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ) {
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }
        //check for a vertical to down streak
        results = countMarksOnAGivenDirection(board, 0, 0, mark, -1, 0);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ) {
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }
        //check for a right-down streak
        results = countMarksOnAGivenDirection(board, 0, 0, mark, -1, 1);
        if ( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ) {
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }

        //check from 3,3 to left, up and left-up
        //check for a horizontal to the left streak
        results = countMarksOnAGivenDirection(board, 2 , 2, mark, 0, -1);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ) {
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }

        //check for a vertical to up streak
        results = countMarksOnAGivenDirection(board, 2, 2, mark, 1, 0);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ) {
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }

        //check for right-down streak
        results = countMarksOnAGivenDirection(board, 0, 2, mark, +1, -1);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ){
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }

        //check for 1,2 to 3,2 streak
        results = countMarksOnAGivenDirection(board, 0, 1, mark, -1, 0);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ){
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }

        //check for 2,1 tp 2,3 streak
        results = countMarksOnAGivenDirection(board, 1, 0, mark, 0, +1);
        if( results[0] == Board.WIN_STREAK-1 && results[1] == 1 ){
            setLocationToWinOrInterrupt(results[2], results[3]);
            return true;
        }
        return false;
    }

    private void setLocationToWinOrInterrupt(int row, int col) {
        locationToWinOrInterrupte[0] = row;
        locationToWinOrInterrupte[1] = col;
    }

    //On A given direction and from a given place,
    //this function counts the amount of a given mark, and the amount of blanks
    private int[] countMarksOnAGivenDirection(Board board, int row, int col, Mark mark, int rowDelta, int colDelta){
        int[] results;
        int blankRow = -1, blankCol = -1, blankCounter = 0;
        int streakCounter = 0;

        while ((row >= 0 && row < Board.SIZE) && (col >= 0 && col < Board.SIZE)){
            //check if the streak is interrupted by the opposite mark
            if (streakCounter > 1){
                if(board.getMark(row, col) == oppositeMark){
                    streakCounter = 0;
                    blankCounter = 0;
                }
            }

            if(board.getMark(row, col) == mark){
                streakCounter++;
            }
            else if(board.getMark(row, col) == Mark.BLANK){
                blankCounter++;
                blankRow = row;
                blankCol = col;
            }
            row += rowDelta;
            col += colDelta;
        }
        results = new int[]{streakCounter, blankCounter, blankRow, blankCol};
        return results;
    }

    @Override
    public String getName() {
        return "clever";
    }
}
