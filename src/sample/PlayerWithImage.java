package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerWithImage {
    private String name;
    private String country;
    private int age;
    private double height;
    private String clubName;
    private String position;
    private int jerseyNumber;
    private double salary;
    private double price;
    private ImageView imageView;

    public PlayerWithImage(Player player2){
        name = player2.getName();
        country = player2.getCountry();
        age = player2.getAge();
        height = player2.getHeight();
        clubName = player2.getClubName();
        position = player2.getPosition();
        jerseyNumber = player2.getJerseyNumber();
        salary = player2.getSalary();
        price = player2.getPrice();
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

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void initImage(){
        try{
            String imagePath = "/images/" + name + ".png";
            Image image = new Image(this.getClass().getResourceAsStream(imagePath));
            imageView = new ImageView(image);
        }catch(Exception e){
            Image image = new Image(this.getClass().getResourceAsStream("/images/placeholder.png"));
            imageView = new ImageView(image);
        }
    }
}
