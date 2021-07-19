package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class SearchPlayers {
    public static List<Player> searchByName(List<Player> playerList, String queryName) {
        List<Player> result = new ArrayList<>();
        for (Player p : playerList) {
            if (p.getName().equalsIgnoreCase(queryName))
                result.add(p);
        }
        if(result.isEmpty()) return null;
        else return result;
    }

    public static List<Player> searchByCountryAndClub(List<Player> playerList, String queryCountry, String queryClub){
        List<Player> result = new ArrayList<>();
        for(Player p : playerList){
            if((p.getCountry().equalsIgnoreCase(queryCountry) || queryCountry.equalsIgnoreCase("ANY")) &&
                    (p.getClubName().equalsIgnoreCase(queryClub) || queryClub.equalsIgnoreCase("ANY")))
                result.add(p);
        }
        if(result.isEmpty()) return null;
        else return result;
    }

    public static List<Player> searchByPosition(List<Player> playerList, String queryPosition) {
        List<Player> result = new ArrayList<>();
        for (Player p : playerList) {
            if (p.getPosition().equalsIgnoreCase(queryPosition))
                result.add(p);
        }
        if(result.isEmpty()) return null;
        else return result;
    }

    public static List<Player> searchBySalary(List<Player> playerList, double lowerBound, double upperBound){
        List<Player> result = new ArrayList<>();
        for(Player p : playerList){
            if(lowerBound <= p.getSalary() && p.getSalary() <= upperBound)
                result.add(p);
        }
        if(result.isEmpty()) return null;
        else return result;
    }

    public static TreeMap<String, Integer> countryCount(List<Player> playerList){
        //TreeMap used instead of HashMap to show the countries in alphabetical order
        TreeMap<String, Integer> counter = new TreeMap<>();
        for(Player p : playerList){
            if(counter.containsKey(p.getCountry()))
                counter.put(p.getCountry(), counter.get(p.getCountry())+1);
            else
                counter.put(p.getCountry(), 1);
        }
        return counter;
    }

    public static List<Player> masterPlayerSearch(List<Player>playerList, String name, String country, String clubName,
                                                  String position, double lowerBound, double upperBound){
        List<Player> result = new ArrayList<>();
        for(Player p : playerList){
            if(!p.getName().equalsIgnoreCase(name) && !name.equalsIgnoreCase("any")  && !p.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                continue;
            else if(!p.getCountry().equalsIgnoreCase(country) && !country.equalsIgnoreCase("any"))
                continue;
            else if(!p.getClubName().equalsIgnoreCase(clubName) && !clubName.equalsIgnoreCase("any"))
                continue;
            else if(!p.getPosition().equalsIgnoreCase(position) && !position.equalsIgnoreCase("any"))
                continue;
            else if(p.getSalary() < lowerBound || p.getSalary() > upperBound)
                continue;

            result.add(p);
        }
        return result;
    }
}
