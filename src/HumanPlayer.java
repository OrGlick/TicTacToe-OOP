import java.util.Scanner;

public class HumanPlayer implements Player{
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(){}

    //ask the user where to put the mark. the function knows the current mark.
    public void playTurn(Board board, Mark mark){
        int[] cors;
        boolean isSuccess;
        do{
            //get coordinates from the user
            cors = getCoordinatesFromUser(mark);
            int row = cors[0], col = cors[1];
            /*try to put the mark.
            decrease the row and the col by 1,
            because user is not aware of the fact indexes starts from 0*/
            isSuccess= board.putMark(mark, row-1, col-1);
            if (!isSuccess) {
                System.out.println("Operation failed. Filled or out of range coordinates");
            }
        }while (!isSuccess);
    }

    private int[] getCoordinatesFromUser(Mark mark){
        System.out.println("Player " + mark + ", please insert cors");
        boolean isSuccess;
        int[] cors = {-1, -1};
        do {
            try {
                String input = scanner.nextLine();
                int intInput = Integer.parseInt(input);
                isSuccess = true;

                //return row and col
                cors = new int[]{intInput/10, intInput%10};
                return cors;

            }
            catch (Exception e){
                System.out.println("please insert a valid number");
                isSuccess = false;
            }
        }while (!isSuccess);

        return cors;
    }

    public String getName(){
        return "Human";
    }

    //choose coordinate on the board
    //put given coordinates on the board from the user
}