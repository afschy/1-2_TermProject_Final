package sample;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private String name;
    private String country;
    private int age;
    private double height;
    private String clubName;
    private String position;
    private int jerseyNumber;
    private double salary;

    public Player(String info){
        //For creating a player directly from file input
        String []parts = info.split(",");
        this.setName(parts[0]);
        this.setCountry(parts[1]);
        this.setAge(Integer.parseInt(parts[2]));
        this.setHeight(Double.parseDouble(parts[3]));
        this.setClubName(parts[4]);
        this.setPosition(parts[5]);
        this.setJerseyNumber(Integer.parseInt(parts[6]));
        this.setSalary(Double.parseDouble(parts[7]));
    }
    public Player(){}
    public Player(Player player2){
        name = player2.name;
        country = player2.country;
        age = player2.age;
        height = player2.height;
        clubName = player2.clubName;
        position = player2.clubName;
        position = player2.position;
        jerseyNumber = player2.jerseyNumber;
        salary = player2.salary;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public String getClubName() {
        return clubName;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString(){
        StringBuffer info = new StringBuffer("Name: " + name + "\n");
        info.append("Country: ").append(country).append("\n");
        info.append("Age (in years): ").append(age).append("\n");
        info.append("Height (in meters): ").append(height).append("\n");
        info.append("Club: ").append(clubName).append("\n");
        info.append("Position: ").append(position).append("\n");
        info.append("Number: ").append(jerseyNumber).append("\n");
        info.append("Weekly Salary: ").append(salary).append("\n");
        return info.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName()) && Objects.equals(getClubName(), player.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getClubName());
    }
}
