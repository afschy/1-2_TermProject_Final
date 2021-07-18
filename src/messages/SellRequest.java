package messages;

import sample.Player;

import java.io.Serializable;

public class SellRequest implements Serializable {
    Player playerToSell;

    public SellRequest(Player playerToSell) {
        this.playerToSell = playerToSell;
    }

    public Player getPlayerToSell() {
        return playerToSell;
    }
}
