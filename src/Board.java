import java.util.Arrays;

public class Board {

    public static final int SIZE = 3;
    public static final int WIN_STREAK = 3;

    private Mark[][] board = new Mark[SIZE][SIZE];
    private int stepsCounter = 0;
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;

    public Board(){
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], Mark.BLANK);
        }
    }

    //put a mark on the given coordinate
    public boolean putMark(Mark mark, int row, int col){
        boolean isSuccess = false;

        //check coordinate validity and if blank
        if(isCoordinateValid(row, col) && board[row][col] == Mark.BLANK){
            //finally put the freaking mark on it's place
            board[row][col] = mark;

            //check success
             isSuccess = board[row][col] == mark;
            if (isSuccess){
                stepsCounter++;

                //check if the game ended
                checkIfTheGameEnded(row, col, mark);
                return isSuccess;
            }
        }
        return isSuccess;
    }

    //return the mark on a given coordinate
    public Mark getMark(int row, int col){
        if(isCoordinateValid(row, col)){
            return board[row][col];
        }
        return Mark.BLANK;
    }

    //return the game status: in progress, X win, O win or draw
    public GameStatus getGameStatus(){
        return gameStatus;
    }


    //main method for checking if the game ended because of a draw or a win
    private void checkIfTheGameEnded(int row, int col, Mark mark){
        //check for a horizontal to the right streak
        if(countMarksOnAGivenDirection(row, col, mark, 0, 1) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a horizontal to the left streak
        if(countMarksOnAGivenDirection(row, col, mark, 0, -1) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a vertical to up streak
        if(countMarksOnAGivenDirection(row, col, mark, 1, 0) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a vertical to down streak
        if(countMarksOnAGivenDirection(row, col, mark, -1, 0) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a right-up streak
        if(countMarksOnAGivenDirection(row, col, mark, 1, 1)  == WIN_STREAK) {
            setWinStatus(mark);
            return;
        }
        //check for a right-down streak
        if (countMarksOnAGivenDirection(row, col, mark, -1, 1) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a left-up streak
        if(countMarksOnAGivenDirection(row, col, mark, -1, -1) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        //check for a left-down streak
        if(countMarksOnAGivenDirection(row, col, mark, 1, -1) == WIN_STREAK){
            setWinStatus(mark);
            return;
        }

        isDraw();
    }

    private void setWinStatus(Mark mark) {
        if(mark == Mark.X)
            gameStatus = GameStatus.X_WIN;
        else
            gameStatus = GameStatus.O_WIN;
    }

    //count the amount of X or O on a given direction (etc. up, down)
    private int countMarksOnAGivenDirection(int row, int col, Mark mark, int rowDelta, int colDelta){
        int streakCounter = 0;
        while ((row >= 0 && row < SIZE) && (col >= 0 && col < SIZE) && board[row][col] == mark){
            if(board[row][col] == mark){
                streakCounter++;
            }
            row += rowDelta;
            col += colDelta;
        }
        return streakCounter;
    }

    //check if draw - if all the board is filled with marks
    private void isDraw(){
        if(stepsCounter == SIZE * SIZE) {
            gameStatus = GameStatus.DRAW;
        }
    }

    //return if the given coordinates are in the range of the board
    private boolean isCoordinateValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
}