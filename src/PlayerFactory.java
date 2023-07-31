public class PlayerFactory {



    public Player buildPlayer(String playerType){

        switch (playerType){
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            default:
                return null;
        }
    }
}
