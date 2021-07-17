package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchClubs {
    public static void initMenu(List<Club> clubList){
        while(true){
            System.out.println("Club Searching Options:");
            System.out.println("(1) Player(s) with the maximum salary of a club");
            System.out.println("(2) Player(s) with the maximum age of a club");
            System.out.println("(3) Player(s) with the maximum height of a club");
            System.out.println("(4) Total yearly salary of a club");
            System.out.println("(5) Back to Main Menu");

            Scanner scn = new Scanner(System.in);
            int userInput = scn.nextInt();
            scn.nextLine();

            if(userInput == 1){
                System.out.print("Enter the name of the club: ");
                String clubName = IOControl.formatName(scn.nextLine());
                Club relevantClub = getRelevantClub(clubList, clubName);
                if(relevantClub == null) System.out.println("No such club with this name");
                else{
                    List<Player> searchResult = maxSalaryPlayers(relevantClub);
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult)
                        System.out.println(p);
                }
            }
            else if(userInput == 2){
                System.out.print("Enter the name of the club: ");
                String clubName = IOControl.formatName(scn.nextLine());
                Club relevantClub =getRelevantClub(clubList, clubName);
                if(relevantClub == null) System.out.println("No such club with this name");
                else{
                    List<Player> searchResult = maxAgePlayers(relevantClub);
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult)
                        System.out.println(p);
                }
            }
            else if(userInput == 3){
                System.out.print("Enter the name of the club: ");
                String clubName = IOControl.formatName(scn.nextLine());
                Club relevantClub = getRelevantClub(clubList, clubName);
                if(relevantClub == null) System.out.println("No such club with this name");
                else{
                    List<Player> searchResult = maxHeightPlayers(relevantClub);
                    System.out.println(searchResult.size() + " match(es) found\n");
                    for (Player p : searchResult)
                        System.out.println(p);
                }
            }
            else if(userInput == 4){
                System.out.print("Enter the name of the club: ");
                String clubName = IOControl.formatName(scn.nextLine());
                Club relevantClub = getRelevantClub(clubList, clubName);
                if(relevantClub == null) System.out.println("No such club with this name");
                else System.out.println("Total yearly salary of " + clubName + " is " + yearlySalary(relevantClub));
            }
            else if(userInput == 5) return;
            else System.out.println("Invalid Input");
        }
    }

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
