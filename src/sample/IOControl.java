package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IOControl {
    public static List<Player> readFromFile(String inputFileName) throws Exception {
        List<Player> playerList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));

        while(true){
            String line = reader.readLine();
            if(line == null) break;
            Player p = new Player(line);
            playerList.add(p);
        }
        reader.close();
        return playerList;
    }

    public static void writeToFile(List<Player> playerList, String outputFileName) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
        for(Player p : playerList){
            writer.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","
                    +p.getClubName()+","+p.getPosition()+","+p.getJerseyNumber()+","+p.getSalary()+"\n");
        }
        writer.close();
    }

    public static String formatName(String name){
        //Formats all names taken from input for easier searching
        //Removes unnecessary spaces and capitalizes individual words
        name = name.trim();
        String []parts = name.split(" ");
        StringBuffer nameBuffer = new StringBuffer();
        int tail = 0;
        for(int i=0; i< parts.length; i++){
            if(!parts[i].isBlank()){
                nameBuffer.append(parts[i].toLowerCase(Locale.ROOT));
                nameBuffer.replace(tail,tail+1,String.valueOf(Character.toUpperCase(nameBuffer.charAt(tail))));
                if(i < parts.length - 1)
                    nameBuffer.append(" ");
                tail += parts[i].length() + 1;
            }
        }
        return nameBuffer.toString();
    }
}
