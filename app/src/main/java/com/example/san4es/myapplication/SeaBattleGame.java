package com.example.san4es.myapplication;



/**
 * Created by Ivan on 12.11.2014.
 */


public class SeaBattleGame {

  public   BattleField battleField;

    public SeaBattleGame(BattleField battleField) {
        this.battleField = battleField;
    }

    public SeaBattleGame(){
        battleField = new BattleField();
        battleField.setField(true);
        battleField.setField(false);
    }

    public boolean attackEnemy(int ip, int jp){
        return battleField.hitEnemy(ip,jp);
    }


    public boolean attackPlayer(int ip, int jp){
        return battleField.hitPlayer( ip,  jp);
    }
    public boolean attackPlayer(){
        return battleField.hitPlayer();
    }


    int getWinner(){
        int[][] player = battleField.getPlayer();
        int[][] enemy = battleField.getEnemy();
        int winner = 1;
        for(int i = 0; i <BattleField.N;++i)
            for(int j = 0; j < BattleField.N;++j) {
                //even one part of ship is still not attacked
                if (enemy[i][j] == BattleField.SHIP)
                    winner = -1;
            }
        if(winner == 1)
            return  winner;
        winner = 2;
        for(int i = 0; i <BattleField.N;++i)
            for(int j = 0; j <BattleField.N;++j) {
                //even one part of ship is still not attacked
                if (player[i][j] == BattleField.SHIP)
                    winner = -1;
            }

        return winner;

    }

    public int[][] getPlayerPlacement(){
        return battleField.getPlayer();
    }
    public int[][] getEnemyPlacement(){
        return battleField.getEnemy();
    }
}
