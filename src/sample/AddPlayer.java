package sample;

import java.util.List;
import java.util.Scanner;

public class AddPlayer {
    public static void initMenu(List<Player> playerList, List<Club> clubList){
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter the player's name: ");
        String inputLine = IOControl.formatName(scn.nextLine());
        if(!verifyName(playerList, inputLine)) {
            System.out.println("ERROR: There is another player with the same name");
            return;
        }
        if(!verifyCharacters(inputLine)){
            System.out.println("ERROR: Invalid name");
            return;
        }
        Player playerToAdd = new Player();
        playerToAdd.setName(inputLine);

        System.out.print("Enter the name of the player's country: ");
        inputLine = IOControl.formatName(scn.nextLine());
        if(!verifyCharacters(inputLine)) {
            System.out.println("ERROR: Invalid country name");
            return;
        }
        playerToAdd.setCountry(inputLine);

        System.out.print("Enter the player's age: ");
        int inputInt;
        try{
            inputInt = scn.nextInt();
            scn.nextLine();
        }catch (Exception e){
            System.out.println("ERROR: Invalid input");
            return;
        }
        if(inputInt <= 0){
            System.out.println("ERROR: Age must be a positive integer");
            return;
        }
        playerToAdd.setAge(inputInt);

        double inputDouble;
        System.out.print("Enter the player's height: ");
        try{
            inputDouble = scn.nextDouble();
            scn.nextLine();
        }catch (Exception e){
            System.out.println("ERROR: Invalid input");
            return;
        }
        if(inputDouble <= 0.0){
            System.out.println("ERROR: Height must be a positive real number");
            return;
        }
        playerToAdd.setHeight(inputDouble);

        System.out.print("Enter the name of the player's club: ");
        inputLine = IOControl.formatName(scn.nextLine());
        if(!verifyCharacters(inputLine)) {
            System.out.println("ERROR: Invalid country name");
            return;
        }
        playerToAdd.setClubName(inputLine);

        System.out.print("Enter the player's position: ");
        inputLine = IOControl.formatName(scn.nextLine());
        if(!verifyPosition(inputLine)){
            System.out.println("ERROR: Invalid Position");
            return;
        }
        playerToAdd.setPosition(inputLine);

        System.out.print("Enter the player's number: ");
        try{
            inputInt = scn.nextInt();
            scn.nextLine();
        }catch (Exception e){
            System.out.println("ERROR: Invalid input");
            return;
        }
        if(inputInt <= 0){
            System.out.println("ERROR: Number must be a positive integer");
            return;
        }
        if(!verifyNumber(playerToAdd.getClubName(), inputInt, playerList)){
            System.out.println("ERROR: " + playerToAdd.getClubName() + " already has a player with this number");
            return;
        }
        playerToAdd.setJerseyNumber(inputInt);

        System.out.print("Enter the player's salary: ");
        try{
            inputDouble = scn.nextDouble();
            scn.nextLine();
        }catch (Exception e){
            System.out.println("ERROR: Invalid input");
            return;
        }
        if(inputDouble <= 0.0){
            System.out.println("ERROR: Weekly salary must be a positive real number");
            return;
        }
        playerToAdd.setSalary(inputDouble);

        if(findClubForPlayer(playerToAdd, clubList))
            playerList.add(playerToAdd);
        else
            System.out.println("ERROR: " + playerToAdd.getClubName() + " already has " + Club.MAX_PLAYER_COUNT + " players");
    }

    public static boolean verifyName(List<Player> playerList, String name){
        if(name.isBlank()) return false;
        for(Player p : playerList){
            if(p.getName().equalsIgnoreCase(name)){
                //System.out.println("ERROR: A player with this name already exists");
                return false;
            }
        }
        return true;
    }

    public static boolean verifyCharacters(String name){
        if(name.isBlank()) return false;
        for(int i=0; i<name.length(); i++){
            if((name.charAt(i)<'A' || (name.charAt(i)>'Z' && name.charAt(i)<'a') || name.charAt(i)>'z') && name.charAt(i)!=' '){
                return false;
            }
        }
        return true;
    }

    public static boolean verifyPosition(String position){
        if(position.equalsIgnoreCase("Goalkeeper")) return true;
        if(position.equalsIgnoreCase("Defender")) return true;
        if(position.equalsIgnoreCase("Midfielder")) return true;
        if(position.equalsIgnoreCase("Forward")) return true;
        return false;
    }

    public static boolean verifyNumber(String clubName, int number, List<Player> playerList){
        for(Player p : playerList){
            if(p.getClubName().equalsIgnoreCase(clubName) && p.getJerseyNumber() == number)
                return false;
        }
        return true;
    }

    public static boolean findClubForPlayer(Player playerToAdd, List<Club> clubList){
        //For adding new players to a suitable club through user input
        //Returns boolean to indicate if the player has been successfully added
        for(Club c : clubList){
            if(c.getName().equalsIgnoreCase(playerToAdd.getClubName()))
                return c.addPlayer(playerToAdd);
        }
        return clubList.add(new Club(playerToAdd));
    }
}
