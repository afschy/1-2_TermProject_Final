package sample;

import java.util.*;

public class SearchPlayers {
    public static void initMenu(List<Player> playerList){
        while(true){
            System.out.println("Player Searching Options:");
            System.out.println("(1) By Player Name");
            System.out.println("(2) By Club and Country");
            System.out.println("(3) By Position");
            System.out.println("(4) By Salary Range");
            System.out.println("(5) Country-wise player count");
            System.out.println("(6) Back to Main Menu");

            Scanner scn = new Scanner(System.in);
            int userInput = scn.nextInt();
            scn.nextLine();

            if(userInput == 1){
                System.out.print("Enter the name of the player: ");
                String name = IOControl.formatName(scn.nextLine());
                List<Player> searchResult = searchByName(playerList,name);
                if(searchResult == null) System.out.println("No such player with this name");
                else {
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult) System.out.println(p);
                }
            }
            else if(userInput == 2){
                System.out.print("Enter the name of the country: ");
                String countryName = IOControl.formatName(scn.nextLine());
                System.out.print("Enter the name of the club: ");
                String clubName = IOControl.formatName(scn.nextLine());
                List<Player> searchResult = searchByCountryAndClub(playerList, countryName, clubName);
                if(searchResult == null) System.out.println("No such player with this country and club");
                else {
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult) System.out.println(p);
                }
            }
            else if(userInput == 3){
                System.out.print("Enter the name of the position: ");
                String position = IOControl.formatName(scn.nextLine());
                List<Player> searchResult = searchByPosition(playerList, position);
                if(searchResult == null) System.out.println("No such player with this position");
                else {
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult) System.out.println(p);
                }
            }
            else if(userInput == 4){
                System.out.print("Enter Lower Bound and Upper Bound in order: ");
                double lowerBound;
                double upperBound;
                try{
                    lowerBound = scn.nextDouble();
                    upperBound = scn.nextDouble();
                }catch (Exception e){
                    System.out.println("ERROR: Invalid input");
                    continue;
                }
                scn.nextLine();
                List<Player> searchResult = searchBySalary(playerList, lowerBound, upperBound);
                if(searchResult == null) System.out.println("No such player within this weekly salary range");
                else {
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult) System.out.println(p);
                }
            }
            else if(userInput == 5){
                TreeMap<String,Integer> counter = countryCount(playerList);
                for(Map.Entry<String, Integer> hash : counter.entrySet())
                    System.out.println(hash.getKey() + " has " + hash.getValue() + " player(s)");
            }
            else if(userInput == 6) return;
            else System.out.println("Invalid Command");
        }
    }

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
