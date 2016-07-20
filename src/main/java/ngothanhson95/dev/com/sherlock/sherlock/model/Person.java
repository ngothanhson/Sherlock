package ngothanhson95.dev.com.sherlock.sherlock.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ngothanhson95 on 7/14/16.
 */
public class Person implements Parcelable{
    private String name;
    private String age;
    private String height;
    private String gender;
    private String hairColour;
    private String address;
    private String additionalComment;
    private int id;
    private byte[] image;
    private int movementCount;

    public Person() {
    }

    //not init movement
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

    public static Creator<Person> getCREATOR() {
        return CREATOR;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getMovementCount() {
        return movementCount;
    }

    public void setMovementCount(int movementCount) {
        this.movementCount = movementCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.age);
        dest.writeString(this.height);
        dest.writeString(this.gender);
        dest.writeString(this.hairColour);
        dest.writeString(this.address);
        dest.writeString(this.additionalComment);
        dest.writeInt(this.id);
        dest.writeByteArray(this.image);
        dest.writeInt(this.movementCount);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readString();
        this.height = in.readString();
        this.gender = in.readString();
        this.hairColour = in.readString();
        this.address = in.readString();
        this.additionalComment = in.readString();
        this.id = in.readInt();
        this.image = in.createByteArray();
        this.movementCount = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
