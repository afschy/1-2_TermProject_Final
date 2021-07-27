package messages;

import sample.Player;

import java.io.Serializable;

public class SellCancelRequest implements Serializable {
    Player player;

    public SellCancelRequest(Player player2){
        this.player = new Player(player2);
    }

    public Player getPlayer(){return player;}
}
