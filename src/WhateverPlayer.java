import java.util.Random;

public class WhateverPlayer implements Player{
    @Override
    public void playTurn(Board board, Mark mark) {
        String[] blankMarksIndexes = getAllBlankMarks(board);

        Random random = new Random();
        int n = random.nextInt(blankMarksIndexes.length);

        String randomChoseBlankIndex = blankMarksIndexes[n];

        //if randomChoseBlankIndex is "0,2", then iIndex will be 0, and jIndex 2.
        //those lines sub sting the number from the String to escape the ",",
        // and then get the String value of the number
        int iIndex = Integer.parseInt(randomChoseBlankIndex.substring(0, 1));
        int jIndex = Integer.parseInt(randomChoseBlankIndex.substring(2, 3));

        board.putMark(mark, iIndex, jIndex);
    }

    @Override
    public String getName() {
        return "Whatever";
    }


    //return a String array with the indexes of the blank/free marks on the board
    private String[] getAllBlankMarks(Board board){
        //get all blank marks
        String[] blankMarksIndexes = new String[Board.SIZE * Board.SIZE];
        int counter = 0;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if(board.getMark(i, j) == Mark.BLANK){
                    blankMarksIndexes[counter] = i + "," + j;
                    counter ++;
                }

            }
        }
        //remove all unused members in the array
        String[] blankMarksIndexesFixed = new String[counter];
        for (int i = 0; i < blankMarksIndexesFixed.length; i++) {
            blankMarksIndexesFixed[i] = blankMarksIndexes[i];
        }
        return blankMarksIndexesFixed;
    }
}
