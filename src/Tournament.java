public class Tournament {
    private final int PLAYER_1_INDEX = 0;
    private final int PLAYER_2_INDEX = 1;
    private Renderer renderer;
    private Player[] players = new Player[2];
    private int rounds;

    public Tournament(Renderer renderer, Player player1, Player player2, int rounds){
        this.renderer = renderer;
        this.players[PLAYER_1_INDEX] = player1;
        this.players[PLAYER_2_INDEX] = player2;
        this.rounds = rounds;
    }

    public int[] playTournament(){
        int[] gamesResults = {0, 0, 0};
        GameStatus gameStatus;
        for (int i = 0; i < rounds; i++) {
            if(i%2 == 0){
                Game game = new Game(players[PLAYER_1_INDEX], players[PLAYER_2_INDEX], renderer);
                gameStatus = game.run();

                switch (gameStatus){
                    case X_WIN:
                        gamesResults[0] ++;
                        break;
                    case O_WIN:
                        gamesResults[1] ++;
                        break;
                    case DRAW:
                        gamesResults[2] ++;
                        break;
                }
            }
            else{
                Game game = new Game(players[PLAYER_2_INDEX], players[PLAYER_1_INDEX], renderer);
                gameStatus = game.run();
                switch (gameStatus){
                    case X_WIN:
                        gamesResults[1] ++;
                        break;
                    case O_WIN:
                        gamesResults[0] ++;
                        break;
                    case DRAW:
                        gamesResults[2] ++;
                        break;
                }
            }

            //print games results
            String winMessage = String.format("%s wins: %d, %s wins: %d, Draws: %d",
                    players[PLAYER_1_INDEX].getName(), gamesResults[0],
                    players[PLAYER_2_INDEX].getName(), gamesResults[1],
                    gamesResults[2]);
            System.out.println(winMessage);
        }
        return gamesResults;
    }

    //indexes of the args of the main
    private static final int NUM_OF_ROUNDS = 0;
    private static final int RENDERER_TYPE = 1;
    private static final int PLAYER_1 = 2;
    private static final int PLAYER_2 = 3;
    private static final int NUM_OF_ARGS = 4;

    private static boolean isNumeric(String strNum) {
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        if(args.length != NUM_OF_ARGS){
            System.out.println("Usage: java Tournament [rounds] [renderer] [player] [player]");
            return;
        }

        if(!isNumeric(args[NUM_OF_ROUNDS])){
            System.out.println("Usage: java Tournament [rounds] [renderer] [player] [player]");
            return;
        }
        int rounds = Integer.parseInt(args[NUM_OF_ROUNDS]);
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();

        Renderer renderer = rendererFactory.buildRenderer(args[1]);
        Player player1 = playerFactory.buildPlayer(args[2]);
        Player player2 = playerFactory.buildPlayer(args[3]);

        if(rounds < 1 || renderer == null || player1 == null || player2 == null){
            System.out.println("Please insert valid rounds, renderer and players");
            return;
        }

        System.out.printf("Rounds: %s, Renderer: %s, Player 1: %s, Player 2: %s%n",
                rounds, renderer.getName(), player1.getName(), player2.getName());
        Tournament tournament = new Tournament(renderer, player1, player2, rounds);
        tournament.playTournament();
    }
}
