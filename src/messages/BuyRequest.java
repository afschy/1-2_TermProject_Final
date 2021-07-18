package messages;

import sample.Player;

import java.io.Serializable;

public class BuyRequest implements Serializable {
    Player playerToBuy;
    String buyerClubName;

    public BuyRequest(Player playerToBuy, String buyerClubName) {
        this.playerToBuy = playerToBuy;
        this.buyerClubName = buyerClubName;
    }

    public Player getPlayerToBuy() {
        return playerToBuy;
    }

    public String getBuyerClubName() {
        return buyerClubName;
    }
}
