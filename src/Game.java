public class Game {
    //connect everything. tell each part what to do

    //the players defined here are just stubs
    private Player[] players = {new HumanPlayer(), new WhateverPlayer()};

    private Renderer renderer;
    private Board board;
    private Mark[] marks = {Mark.X, Mark.O};

    public Game(Player playerX, Player playerO, Renderer renderer){
        players[0] = playerX;
        players[1] = playerO;
        this.renderer = renderer;
        this.board = new Board();
    }

    public GameStatus run(){
        int counter = 0;
        while (board.getGameStatus() == GameStatus.IN_PROGRESS){
            players[counter%2].playTurn(board, marks[counter%2]);
            counter++;
            renderer.renderBoard(board);
        }
        return board.getGameStatus();
    }
}
