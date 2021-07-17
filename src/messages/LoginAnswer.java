package messages;

import sample.Club;

import java.io.Serializable;

public class LoginAnswer implements Serializable {
    sample.Club club;
    boolean status;

    public LoginAnswer(Club club, boolean status) {
        this.club = club;
        this.status = status;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
