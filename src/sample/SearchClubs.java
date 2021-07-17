package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchClubs {
    public static Club getRelevantClub(List<Club> clubList, String queryName){
        //Returns the club with matching name; returns null if no such club is found
        for(Club c : clubList){
            if(c.getName().equalsIgnoreCase(queryName))
                return c;
        }
        return null;
    }

    public static List<Player> maxSalaryPlayers(Club relevantClub){
        double maxSalary = -1;
        for(Player p : relevantClub.getPlayers()){
            if(p.getSalary() > maxSalary) maxSalary = p.getSalary();
        }
        List<Player> playerList = new ArrayList<>();
        for(Player p : relevantClub.getPlayers()){
            if(p.getSalary() == maxSalary)
                playerList.add(p);
        }
        return playerList;
    }

    public static List<Player> maxAgePlayers(Club relevantClub){
        int maxAge = -1;
        for(Player p : relevantClub.getPlayers()){
            if(p.getAge() > maxAge) maxAge = p.getAge();
        }
        List<Player> playerList = new ArrayList<>();
        for(Player p : relevantClub.getPlayers()){
            if(p.getAge() == maxAge)
                playerList.add(p);
        }
        return playerList;
    }

    public static List<Player> maxHeightPlayers(Club relevantClub){
        double maxHeight = -1;
        for(Player p : relevantClub.getPlayers()){
            if(p.getHeight() > maxHeight) maxHeight = p.getHeight();
        }
        List<Player> playerList = new ArrayList<>();
        for(Player p : relevantClub.getPlayers()){
            if(p.getHeight() == maxHeight)
                playerList.add(p);
        }
        return playerList;
    }

    public static double yearlySalary(Club relevantClub){
        double sum = 0;
        for(Player p : relevantClub.getPlayers()){
            sum += p.getSalary()*52;
        }
        return sum;
    }
}
