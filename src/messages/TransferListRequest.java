package messages;

import java.io.Serializable;

public class TransferListRequest implements Serializable {
    String clubName;

    public TransferListRequest(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }
}
