package yellosoft.movie_star;

/**
 * Created by YelloSOFT on 09.07.2017.
 */

public class Stars {

    private String name;
    private String floor;
    private String age;
    private int image;

    public Stars(String name, String floor, String age, int image){

        this.name=name;
        this.floor = floor;
        this.age=age;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfloor() {
        return this.floor;
    }

    public void setfloor(String floor) {
        this.floor = floor;
    }
    public String getage() {
        return this.age;
    }

    public void setage(String age) {
        this.age = age;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
