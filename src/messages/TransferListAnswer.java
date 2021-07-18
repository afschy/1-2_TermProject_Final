package messages;

import sample.Club;
import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransferListAnswer implements Serializable {
    List<sample.Player> transferList;
    sample.Club club;

    public TransferListAnswer(List<Player> transferList, sample.Club club) {
        this.transferList = new ArrayList<>(transferList);
        this.club = new sample.Club(club);
    }

    public List<Player> getTransferList() {
        return transferList;
    }
    public Club getClub() {
        return club;
    }
}
