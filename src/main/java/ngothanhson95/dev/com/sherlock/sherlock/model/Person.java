package ngothanhson95.dev.com.sherlock.sherlock.model;

import android.graphics.Bitmap;

/**
 * Created by ngothanhson95 on 7/14/16.
 */
public class Person {
    private String name;
    private String age;
    private String height;
    private String gender;
    private String hairColour;
    private String address;
    private String additionalComment;
    private Bitmap bmp;

    public Person() {
    }

    public Person(String additionalComment, String address, String age, Bitmap bmp, String gender, String hairColour, String height, String name) {
        this.additionalComment = additionalComment;
        this.address = address;
        this.age = age;
        this.bmp = bmp;
        this.gender = gender;
        this.hairColour = hairColour;
        this.height = height;
        this.name = name;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
