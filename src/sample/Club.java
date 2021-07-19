package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    private String name;
    public static final int MAX_PLAYER_COUNT = 7;
    private List<Player> players;


    public Club(Player player){
        name = player.getClubName();
        players = new ArrayList<>();
        players.add(player);
    }
    public Club(String name){
        this.setName(name);
        players = new ArrayList<>();
    }
    public Club(Club club2){
        name = club2.name;
        players = new ArrayList<>();
        for(Player p : club2.players)
            players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public boolean addPlayer(Player player){
        //Verifies and adds a new player
        //Returns boolean for deciding if the player is to be added to the list of players
        if(players.size() >= MAX_PLAYER_COUNT){
            return false;
        }
        players.add(player);
        return true;
    }

    public static List<Club> createClubList(List<Player> playerList){
        //Creates a list of clubs form the list of players taken from input file
        //Creates new clubs if the required club does not exist, else adds player to an existing club
        List<Club> clubList = new ArrayList<>();
        for(Player p : playerList){
            boolean flag = true;
            for(Club c : clubList){
                if(c.getName().equalsIgnoreCase(p.getClubName())){
                    flag = false;
                    //c.addPlayer(p);
                    c.getPlayers().add(p);
                }
            }
            if(flag)
                clubList.add(new Club(p));
        }
        return clubList;
    }

    public String toString(){
        StringBuffer show = new StringBuffer("Name: " + name + "\n");
        show.append("Number of players: ").append(players.size()).append("\n");
        for(Player p : players)
            show.append(p.toString());
        return show.toString();
    }
}
