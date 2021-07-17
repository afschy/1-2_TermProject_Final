package messages;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private String clubName;

    public LoginRequest(String clubName){
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
}
