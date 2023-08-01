public class PlayerFactory {



    public Player buildPlayer(String playerType){

        switch (playerType){
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            default:
                return null;
        }
    }
}
