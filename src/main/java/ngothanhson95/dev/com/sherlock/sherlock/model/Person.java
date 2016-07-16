package ngothanhson95.dev.com.sherlock.sherlock.model;

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
    private int id;
    private byte[] image;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String additionalComment, String address, String age, String gender, String hairColour, String height, byte[] image, String name) {
        this.additionalComment = additionalComment;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.hairColour = hairColour;
        this.height = height;
        this.image = image;
        this.name = name;
    }
}
